package com.astrick.compose.lists.paging.data

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.astrick.compose.lists.paging.data.remote.GithubSearchRemoteDataSource
import com.astrick.compose.lists.paging.data.local.RepoDatabase
import com.astrick.compose.lists.paging.data.remote.GithubSearchItemModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update

/**
 * Repository class that works with local and remote data sources.
 */
@OptIn(ExperimentalCoroutinesApi::class, ExperimentalPagingApi::class)
class GithubSearchRepository(
    private val remoteDataSource: GithubSearchRemoteDataSource,
    private val database: RepoDatabase
) {
    
    private val queryFlow = MutableStateFlow("")
    fun updateQuery(query: String) {
        queryFlow.update { query }
    }
    
    /**
     * Search repositories whose names match the query, exposed as a stream of data that will emit
     * every time we get more data from the network.
     */
    fun getPagingFlow(): Flow<PagingData<GithubSearchItemModel>> {
        Log.d("GithubRepository", "New query: ${queryFlow.value}")
        
        return queryFlow
            .distinctUntilChanged { old, new ->
                old == new
            } // Only emit when the query changes
            .flatMapLatest { query ->
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
            }
    }
    
    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}
