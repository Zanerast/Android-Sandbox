package com.astrick.sandbox.integrations.paging

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.astrick.sandbox.integrations.paging.ui.GithubSearchViewModel
import com.astrick.sandbox.integrations.paging.ui.SearchResultsContent
import com.astrick.sandbox.integrations.paging.ui.models.UiListItem
import kotlinx.coroutines.flow.flowOf

@Composable
fun PagingScreen(
    initialQuery: String,
    viewModel: GithubSearchViewModel
) {
    val lazyItems = viewModel.pagingData.collectAsLazyPagingItems()
    
    PagingContent(
        lazyItems = lazyItems,
        initialQuery = initialQuery,
        onQueryChanged = {
            viewModel.searchForQuery(it)
        }
    )
}

@Composable
private fun PagingContent(
    initialQuery: String,
    onQueryChanged: (query: String) -> Unit,
    lazyItems: LazyPagingItems<UiListItem>
) {
    
    val (text, onSearchChanged) = remember {
        mutableStateOf(initialQuery)
    }
    Column {
        Row {
            TextField(
                value = text,
                onValueChange = onSearchChanged,
                trailingIcon = {
                    IconButton(
                        onClick = {
                            onQueryChanged(text)
                        }
                    ) {
                        Image(imageVector = Icons.Filled.Search, contentDescription = null)
                    }
                }
            )
        }
        SearchResultsContent(lazyItems)
    }
}


@Preview(showSystemUi = true)
@Composable
private fun MainPreview() {
    PagingContent(
        lazyItems = flowOf<PagingData<UiListItem>>().collectAsLazyPagingItems(),
        initialQuery = "Hello World",
        onQueryChanged = {}
    )
}
