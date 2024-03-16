package com.astrick.compose.lists.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.astrick.compose.lists.paging.data.GithubSearchRepository
import com.astrick.compose.lists.paging.data.remote.GithubSearchItemModel
import kotlinx.coroutines.flow.map

class GithubSearchViewModel(private val repository: GithubSearchRepository) : ViewModel() {
    
    val pagingData = repository.getPagingFlow()
        .map { pagingData -> pagingData.map { UiModel.RepoItem(it) } }
        .map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators null
                }
                
                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                }
                // check between 2 items
                if (before.roundedStarCount > after.roundedStarCount) {
                    if (after.roundedStarCount >= 1) {
                        UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                    } else {
                        UiModel.SeparatorItem("< 10.000+ stars")
                    }
                } else {
                    // no separator
                    null
                }
            }
        }
        .cachedIn(viewModelScope)
    
    fun searchForQuery(queryString: String) {
        repository.updateQuery(queryString)
    }
    
}

sealed class UiModel {
    data class RepoItem(val githubSearchItemModel: GithubSearchItemModel) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}

private val UiModel.RepoItem.roundedStarCount: Int
    get() = this.githubSearchItemModel.stars / 10_000
