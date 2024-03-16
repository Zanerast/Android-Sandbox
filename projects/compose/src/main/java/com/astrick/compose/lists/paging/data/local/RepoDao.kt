package com.astrick.compose.lists.paging.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.astrick.compose.lists.paging.data.remote.GithubSearchItemModel

@Dao
interface RepoDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(githubSearchItemModels: List<GithubSearchItemModel>)
    
    @Query(
        "SELECT * FROM repos WHERE " +
                "name LIKE :queryString OR description LIKE :queryString " +
                "ORDER BY stars DESC, name ASC"
    )
    fun reposByName(queryString: String): PagingSource<Int, GithubSearchItemModel>
    
    @Query("DELETE FROM repos")
    suspend fun clearRepos()
}
