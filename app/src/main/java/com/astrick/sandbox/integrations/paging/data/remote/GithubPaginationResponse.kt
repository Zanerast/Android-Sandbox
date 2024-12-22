package com.astrick.sandbox.integrations.paging.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data class representing the response for GitHub pagination.
 *
 * @property total the total number of repositories found
 * @property items the list of repository details for the current page
 */
@JsonClass(generateAdapter = true)
data class GithubPaginationResponse(
    @Json(name = "total_count")
    val total: Int = 0,
    val items: List<GithubRepoDetailsResponse> = emptyList(),
)
