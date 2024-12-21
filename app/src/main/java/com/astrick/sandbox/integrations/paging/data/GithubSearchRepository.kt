package com.astrick.sandbox.integrations.paging.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.astrick.sandbox.integrations.paging.data.remote.GithubRemoteApi
import com.astrick.sandbox.integrations.paging.data.local.RepoDatabase
import com.astrick.sandbox.integrations.paging.data.local.toModel
import com.astrick.sandbox.integrations.paging.domain.GithubRepoDetails
import com.astrick.sandbox.integrations.paging.domain.GithubSearchRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/**
 * Repository class that works with local and remote data sources.
 */
@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class GithubSearchRepoImpl(
    private val remoteDataSource: GithubRemoteApi,
    private val database: RepoDatabase
): GithubSearchRepo {
    
    private val queryFlow = MutableStateFlow("")
    override fun updateQuery(query: String) {
        queryFlow.update { query }
    }

    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    override fun getPagingFlow(): Flow<PagingData<GithubRepoDetails>> {
        return queryFlow
            .distinctUntilChanged { old, new ->
                old == new
            }.flatMapLatest { query ->
                val betterQuery = "%${query.replace(' ', '%')}%"
                
                Pager(
                    config = PagingConfig(
                        pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false
                    ),
                    remoteMediator = GithubSearchRemoteMediator(
                        betterQuery, remoteDataSource, database
                    ),
                    pagingSourceFactory = {
                        database.reposDao().reposByName(betterQuery)
                    },
                ).flow
            }.map { pagingData ->
                pagingData.map { it.toModel() }
            }
    }
    
    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}
