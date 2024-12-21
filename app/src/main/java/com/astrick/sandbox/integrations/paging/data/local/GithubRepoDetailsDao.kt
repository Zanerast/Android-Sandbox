package com.astrick.sandbox.integrations.paging.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO for interacting with the GitHub repo details in the database.
 */
@Dao
interface GithubRepoDetailsDao {

    /**
     * Inserts a list of GitHub repo details into the database, replacing existing entries on conflict.
     *
     * @param githubSearchItemModels the list of GitHub repository details to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(githubSearchItemModels: List<GithubRepoDetailsEntity>)

    /**
     * Queries the database for repos whose name or description matches the provided query string.
     * Results are ordered by stars in descending order, then by name in ascending order.
     *
     * @param queryString the query string to match against repo names and descriptions
     * @return a PagingSource for paged results of GitHub repository details
     */
    @Query(
        """
        SELECT * 
        FROM repos 
        WHERE name LIKE :queryString 
        OR description LIKE :queryString 
        ORDER BY stars DESC, name ASC
        """
    )
    fun reposByName(queryString: String): PagingSource<Int, GithubRepoDetailsEntity>

    /**
     * Clears all repo details from the database.
     */
    @Query("DELETE FROM repos")
    suspend fun clearRepos()

}
