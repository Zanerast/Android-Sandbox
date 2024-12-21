package com.astrick.sandbox.integrations.paging.ui.models

import com.astrick.sandbox.integrations.paging.domain.GithubRepoDetails

/**
 * Represents a UI item in a list, which can either be a repo or a separator.
 */
sealed class UiListItem {
    /**
     * A UI item representing a GitHub repository.
     *
     * @property githubRepoDetails Details of the repository.
     */
    data class RepoItem(val githubRepoDetails: GithubRepoDetails) : UiListItem() {
        /**
         * The star count of the repo, rounded down to the nearest ten thousand.
         */
        val roundedStarCount: Int
            get() = this.githubRepoDetails.stars / 10_000
    }


    /**
     * A UI item serving as a separator in the list.
     *
     * @property description The description displayed for the separator.
     */
    data class SeparatorItem(val description: String) : UiListItem()
}
