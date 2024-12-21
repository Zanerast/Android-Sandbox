package com.astrick.sandbox.integrations.paging.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing the remote keys for pagination in the database.
 *
 * @property repoId the unique identifier of the repo
 * @property prevKey the previous page key for pagination, if available
 * @property nextKey the next page key for pagination, if available
 */
@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
