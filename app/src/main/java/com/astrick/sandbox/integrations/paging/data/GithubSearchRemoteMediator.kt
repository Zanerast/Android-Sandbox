package com.astrick.sandbox.integrations.paging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.astrick.sandbox.integrations.paging.data.local.GithubRepoDetailsEntity
import com.astrick.sandbox.integrations.paging.data.local.RemoteKeysEntity
import com.astrick.sandbox.integrations.paging.data.local.RepoDatabase
import com.astrick.sandbox.integrations.paging.data.remote.GithubRemoteApi
import com.astrick.sandbox.integrations.paging.data.remote.toEntity

/**
 * Handles paging and caching of GitHub search results from the network and database.
 *
 * Reference docs: developer.android.com/reference/kotlin/androidx/paging/RemoteMediator
 *
 * @property query The search query string.
 * @property service The GitHub API service used for fetching data.
 * @property repoDatabase The local database for caching repository data.
 */
@OptIn(ExperimentalPagingApi::class)
class GithubSearchRemoteMediator(
    private val query: String,
    private val service: GithubRemoteApi,
    private val repoDatabase: RepoDatabase
) : RemoteMediator<Int, GithubRepoDetailsEntity>() {

    /**
     * Determines the initial action when paging starts.
     *
     * @return [InitializeAction.LAUNCH_INITIAL_REFRESH] to refresh data from the network.
     */
    override suspend fun initialize(): InitializeAction {
        // In cases where we don't mind showing out-of-date, cached offline data, we can return
        // SKIP_INITIAL_REFRESH instead to prevent paging triggering a remote refresh.
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    /**
     * Loads data for the specified [LoadType] and manages database updates.
     *
     * @param loadType The type of load operation: [LoadType.REFRESH], [LoadType.PREPEND], or [LoadType.APPEND].
     * @param state The current state of the paging system.
     * @return [MediatorResult] indicating success or error.
     */
    override suspend fun load(loadType: LoadType, state: PagingState<Int, GithubRepoDetailsEntity>): MediatorResult {
        val remoteKeysForFirstItem = getRemoteKeyForFirstItem(state)
        if (loadType == LoadType.PREPEND && remoteKeysForFirstItem?.isAtStartOfPagination == true) {
            return MediatorResult.Success(endOfPaginationReached = false)
        }

        val page = loadType.getPage(state)
            ?: return MediatorResult.Success(endOfPaginationReached = true)

        val apiQuery = "$query+in:name,description"

        try {
            val apiResponse = service.searchRepos(apiQuery, page, state.config.pageSize)

            val repos = apiResponse.items
            val endOfPaginationReached = repos.isEmpty()

            repoDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    repoDatabase.remoteKeysDao().clearRemoteKeys()
                    repoDatabase.reposDao().clearRepos()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                // Save Remote key details
                val keys = repos.map {
                    RemoteKeysEntity(repoId = it.id, prevIndex = prevKey, nextIndex = nextKey)
                }
                repoDatabase.remoteKeysDao().insertAll(keys)

                // Save repo details
                val repoEntities = repos.map { it.toEntity() }
                repoDatabase.reposDao().insertAll(repoEntities)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: Exception) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun LoadType.getPage(
        state: PagingState<Int, GithubRepoDetailsEntity>
    ): Int? = when (this) {
        LoadType.REFRESH -> {
            val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
            remoteKeys?.nextIndex?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
        }

        LoadType.PREPEND -> {
            val remoteKeys = getRemoteKeyForFirstItem(state)
            remoteKeys?.prevIndex
        }

        LoadType.APPEND -> {
            val remoteKeys = getRemoteKeyForLastItem(state)
            remoteKeys?.nextIndex
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, GithubRepoDetailsEntity>): RemoteKeysEntity? {
        return state.pages.sortedBy { it.nextKey }
            .lastOrNull() { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { repo -> repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id) }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, GithubRepoDetailsEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo -> repoDatabase.remoteKeysDao().remoteKeysRepoId(repo.id) }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, GithubRepoDetailsEntity>
    ): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                repoDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }

    companion object {
        private const val GITHUB_STARTING_PAGE_INDEX = 1
    }

}
