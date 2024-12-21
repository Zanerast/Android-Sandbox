package com.astrick.sandbox.integrations.paging.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.astrick.sandbox.integrations.paging.domain.GithubRepoDetails

/**
 * Entity representing details about a GitHub repo for database storage.
 *
 * @property id the unique identifier of the repository
 * @property name the name of the repository
 * @property fullName the full name of the repository (e.g., owner/repo)
 * @property description the description of the repository, if available
 * @property url the URL of the repository
 * @property stars the number of stars the repository has
 * @property forks the number of forks the repository has
 * @property language the programming language of the repository, if available
 */
@Entity(tableName = "repos")
data class GithubRepoDetailsEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String?
)

/**
 * Converts a [GithubRepoDetailsEntity] to a [GithubRepoDetails].
 *
 * @return the [GithubRepoDetails] representation of the entity
 */
fun GithubRepoDetailsEntity.toModel() = GithubRepoDetails(
    name = name,
    fullName = fullName,
    description = description,
    url = url,
    stars = stars,
    forks = forks,
    language = language
)
