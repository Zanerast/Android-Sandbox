package com.astrick.sandbox.integrations.paging.data.remote

import com.astrick.sandbox.integrations.paging.data.local.GithubRepoDetailsEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents the detailed response of a GitHub repository fetched from the API.
 *
 * @property id The unique identifier of the repository.
 * @property name The short name of the repository.
 * @property fullName The full name of the repository, including the owner.
 * @property description A brief description of the repository, if available.
 * @property url The URL to the repository on GitHub.
 * @property stars The number of stars the repository has received.
 * @property forks The number of times the repository has been forked.
 * @property language The primary programming language of the repository, if specified.
 */
@JsonClass(generateAdapter = true)
data class GithubRepoDetailsResponse(
    val id: Long,
    val name: String,
    @Json(name = "full_name")
    val fullName: String,
    val description: String?,
    @Json(name = "html_url")
    val url: String,
    @Json(name = "stargazers_count")
    val stars: Int,
    @Json(name = "forks_count")
    val forks: Int,
    val language: String?
)

/**
 * Converts a [GithubRepoDetailsResponse] instance to a [GithubRepoDetailsEntity].
 *
 * @return A [GithubRepoDetailsEntity] containing the mapped properties from this response object.
 */
fun GithubRepoDetailsResponse.toEntity(): GithubRepoDetailsEntity {
    return GithubRepoDetailsEntity(
        id = id,
        name = name,
        fullName = fullName,
        description = description ?: "",
        url = url,
        stars = stars,
        forks = forks,
        language = language ?: ""
    )
}
