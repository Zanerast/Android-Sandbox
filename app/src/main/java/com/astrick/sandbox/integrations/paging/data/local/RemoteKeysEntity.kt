package com.astrick.sandbox.integrations.paging.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing the remote keys for pagination in the database.
 *
 * @property repoId the unique identifier of the repo
 * @property prevIndex the previous page index for pagination, null if at start of pagination
 * @property nextIndex the next page index for pagination, null if at end of pagination
 */
@Entity(tableName = "remote_keys")
data class RemoteKeysEntity(
    @PrimaryKey
    val repoId: Long,
    val prevIndex: Int?,
    val nextIndex: Int?
) {
    /**
     * Checks if the current pagination is at the end.
     *
     * @return `true` if `prevIndex` is `null`, indicating that this is the first page in the pagination.
     */
    val isAtStartOfPagination: Boolean
        get() = prevIndex == null

}
