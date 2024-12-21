package com.astrick.sandbox.integrations.paging.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.astrick.sandbox.integrations.paging.ui.widgets.GithubSearchItem
import com.astrick.sandbox.integrations.paging.ui.components.SeparatorItem
import com.astrick.sandbox.integrations.paging.ui.models.UiListItem

@Composable
fun SearchResultsContent(
    lazyItems: LazyPagingItems<UiListItem>
) {
    LazyColumn {
        if (lazyItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Please wait for items to load",
                    modifier = Modifier
                        .wrapContentWidth()
                )
            }
        }
        
        items(
            count = lazyItems.itemCount,
            key = lazyItems.itemKey { model ->
                when (model) {
                    is UiListItem.RepoItem -> {
                        model.githubRepoDetails.name
                    }
                    
                    is UiListItem.SeparatorItem -> {
                        model.description
                    }
                }
            }
        ) { index ->
            val item = lazyItems[index]
            if (item is UiListItem.RepoItem) {
                GithubSearchItem(githubRepoDetails = item.githubRepoDetails)
            } else if (item is UiListItem.SeparatorItem) {
                SeparatorItem(separatorItem = item)
            }
        }
        
        if (lazyItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                )
            }
        }
    }
}
