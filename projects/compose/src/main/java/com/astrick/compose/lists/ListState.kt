package com.astrick.compose.lists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
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
import com.astrick.core.ui.theme.SandboxTheme
import kotlinx.coroutines.launch

private val names = listOf(
    "Abby", "John", "Emily", "Michael", "Sophia", "Daniel", "Emma", "Matthew", "Olivia", "William",
    "Ava", "James", "Isabella", "Alexander", "Mia", "Benjamin", "Charlotte", "Ethan", "Amelia",
    "Henry",
    "Liam", "Madison", "Noah", "Ella", "Logan", "Grace", "Lucas", "Chloe", "Jackson", "Avery",
    "Jack", "Sofia", "Elijah", "Lily", "Carter", "Hannah", "Mason", "Scarlett", "Evelyn", "Samuel",
    "Grace", "Gabriel", "Sophie", "Owen", "Aria", "Jacob", "Natalie", "David", "Leah", "Lucy",
    "Andrew", "Victoria", "Luke", "Zoe", "Ryan", "Penelope", "Nathan", "Ariana", "Dylan", "Madeline"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListState(
    threshold: Int = 0
) {
    val listState = rememberLazyListState()
    val sortedNames = names.sortedBy { it.first() }
    val showButton by remember(threshold) {
        // derivedStateOf {} should be used when your state or key is
        // changing more than you want to update your UI.
        // derivedStateOf is like distinctUntilChanged.
        // Be careful if threshold could change and you are not using the remember key
        // https://medium.com/androiddevelopers/jetpack-compose-when-should-i-use-derivedstateof-63ce7954c11b
        derivedStateOf { listState.firstVisibleItemIndex > threshold }
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            AnimatedVisibility(visible = showButton) {
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
            items(sortedNames) {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = it,
                        modifier = Modifier.padding(18.dp)
                    )
                }
            }
        }
    }
    
}

@SandboxPreviews
@Composable
private fun MainPreview() {
    SandboxTheme {
        StickyHeaders()
    }
}
