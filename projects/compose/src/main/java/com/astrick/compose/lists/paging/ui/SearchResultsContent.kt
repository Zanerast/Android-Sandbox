package com.astrick.compose.lists.paging.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.astrick.compose.lists.paging.ui.items.GithubSearchItem
import com.astrick.compose.lists.paging.ui.items.SeparatorItem

@Composable
fun SearchResultsContent(
    lazyItems: LazyPagingItems<UiModel>
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
                    is UiModel.RepoItem -> {
                        model.githubSearchItemModel.id
                    }
                    
                    is UiModel.SeparatorItem -> {
                        model.description
                    }
                }
            }
        ) { index ->
            val item = lazyItems[index]
            if (item is UiModel.RepoItem) {
                GithubSearchItem(githubSearchItemModel = item.githubSearchItemModel)
            } else if (item is UiModel.SeparatorItem) {
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
