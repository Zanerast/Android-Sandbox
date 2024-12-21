package com.astrick.sandbox.integrations.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.astrick.sandbox.integrations.paging.domain.GithubSearchRepo
import com.astrick.sandbox.integrations.paging.ui.models.UiListItem
import kotlinx.coroutines.flow.map

/**
 * ViewModel for managing GitHub search and displaying paginated results.
 *
 * @property repository The repository used to fetch and manage GitHub search data.
 */
class GithubSearchViewModel(private val repository: GithubSearchRepo) : ViewModel() {

    /**
     * A flow of paginated data that maps repository items to UI list items.
     * Separators are added between groups of items based on star count ranges.
     */
    val pagingData = repository.getPagingFlow()
        .map { pagingData -> pagingData.map { UiListItem.RepoItem(it) } }
        .map {
            it.insertSeparators { before, after ->
                when {
                    after == null -> null // we're at the end of the list
                    before == null -> UiListItem.SeparatorItem("${after.roundedStarCount}0.000+ stars")  // we're at the beginning of the list
                    before.roundedStarCount > after.roundedStarCount -> {
                        if (after.roundedStarCount >= 1) {
                            UiListItem.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                        } else {
                            UiListItem.SeparatorItem("< 10.000+ stars")
                        }
                    }
                    else -> null
                }
            }
        }
        .cachedIn(viewModelScope)
    
    fun searchForQuery(queryString: String) {
        repository.updateQuery(queryString)
    }
    
}
