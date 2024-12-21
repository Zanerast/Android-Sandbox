package com.astrick.sandbox.integrations.paging.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * DAO for interacting with the remote keys used for pagination in the database.
 */
@Dao
interface RemoteKeysDao {

    /**
     * Inserts a list of remote keys into the database, replacing existing entries on conflict.
     *
     * @param remoteKey the list of remote keys to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    /**
     * Queries the database for a remote key by the repo ID.
     *
     * @param repoId the repository ID to search for
     * @return the corresponding remote key, or null if not found
     */
    @Query("SELECT * FROM remote_keys WHERE repoId = :repoId")
    suspend fun remoteKeysRepoId(repoId: Long): RemoteKeysEntity?

    /**
     * Clears all remote keys from the database.
     */
    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
