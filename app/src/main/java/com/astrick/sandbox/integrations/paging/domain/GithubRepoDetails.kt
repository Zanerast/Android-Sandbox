package com.astrick.sandbox.integrations.paging.domain

/**
 * Represents the details of a GitHub repository.
 *
 * @property name The repository's name.
 * @property fullName The full name of the repository, including the owner's name.
 * @property description A brief description of the repository.
 * @property url The URL to access the repository.
 * @property stars The number of stars the repository has received.
 * @property forks The number of times the repository has been forked.
 * @property language The primary programming language used in the repository.
 */
data class GithubRepoDetails(
    val name: String,
    val fullName: String,
    val description: String,
    val url: String,
    val stars: Int,
    val forks: Int,
    val language: String
)
