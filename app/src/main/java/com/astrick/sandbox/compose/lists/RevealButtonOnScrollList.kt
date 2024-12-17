package com.astrick.sandbox.compose.lists

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Card
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrick.compose.annotations.SandboxPreviews
import com.astrick.compose.lists.StickyHeaders
import com.astrick.core.ui.theme.SandboxTheme
import kotlinx.coroutines.launch

/**
 * Displays a floating action button that becomes visible when the list is scrolled beyond a specified threshold.
 * The button scrolls the list back to the top when clicked.
 *
 * @param threshold The index threshold for when the button becomes visible. Defaults to 0.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RevealButtonOnScrollList(
    threshold: Int = 0
) {
    val scope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val sortedNames = names.sortedBy { it.first() }
    val showButton by remember(threshold) {
        // derivedStateOf {} should be used when your state or key is
        // changing more than you want to update your UI.
        // derivedStateOf is like distinctUntilChanged.
        // Be careful if threshold could change and you are not using the remember key
        // Ref: medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
        derivedStateOf { listState.firstVisibleItemIndex > threshold }
    }
    Scaffold(
        floatingActionButton = {
            if (showButton) {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Jump to top") },
                    icon = {
                        Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "Up")
                    },
                    onClick = {
                        scope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                )
            }
        }
    ) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState
        ) {
            stickyHeader {
                Header(listState, sortedNames)
            }
            items(sortedNames) { item ->
                Item(item)
            }
        }
    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
private fun Header(
    listState: LazyListState,
    sortedNames: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp)
    ) {
        Text(
            text = "First visible item index: ${listState.firstVisibleItemIndex}\n" +
                    "First visible item scroll offset: ${listState.firstVisibleItemScrollOffset}\n" +
                    "Name: ${sortedNames[listState.firstVisibleItemIndex]}",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Item(it: String) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = it,
            modifier = Modifier.padding(18.dp)
        )
    }
}


@SandboxPreviews
@Composable
private fun MainPreview() {
    SandboxTheme {
        StickyHeaders()
    }
}

private val names = listOf(
    "Abby", "John", "Emily", "Michael", "Sophia", "Daniel", "Emma", "Matthew", "Olivia", "William",
    "Ava", "James", "Isabella", "Alexander", "Mia", "Benjamin", "Charlotte", "Ethan", "Amelia", "Henry",
    "Liam", "Madison", "Noah", "Ella", "Logan", "Grace", "Lucas", "Chloe", "Jackson", "Avery",
    "Jack", "Sofia", "Elijah", "Lily", "Carter", "Hannah", "Mason", "Scarlett", "Evelyn", "Samuel",
    "Grace", "Gabriel", "Sophie", "Owen", "Aria", "Jacob", "Natalie", "David", "Leah", "Lucy",
    "Andrew", "Victoria", "Luke", "Zoe", "Ryan", "Penelope", "Nathan", "Ariana", "Dylan", "Madeline"
)
