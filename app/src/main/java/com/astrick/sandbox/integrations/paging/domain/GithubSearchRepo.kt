package com.astrick.sandbox.integrations.paging.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that works with local and remote data sources.
 */
interface GithubSearchRepo{

    /**
     * Updates the query for searching repos.
     *
     * @param query the search query string
     */
    fun updateQuery(query: String)

    /**
     * Returns a flow of paged repository details.
     *
     * @return a flow containing PagingData of GithubRepoDetails
     */
    fun getPagingFlow(): Flow<PagingData<GithubRepoDetails>>

}
