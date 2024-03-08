package com.astrick.compose.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.astrick.compose.annotations.SandboxPreviews
import com.astrick.core.ui.theme.SandboxTheme

private val names = listOf(
    "Abby", "John", "Emily", "Michael", "Sophia", "Daniel", "Emma", "Matthew", "Olivia", "William",
    "Ava", "James", "Isabella", "Alexander", "Mia", "Benjamin", "Charlotte", "Ethan", "Amelia", "Henry",
    "Liam", "Madison", "Noah", "Ella", "Logan", "Grace", "Lucas", "Chloe", "Jackson", "Avery",
    "Jack", "Sofia", "Elijah", "Lily", "Carter", "Hannah", "Mason", "Scarlett", "Evelyn", "Samuel"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StickyHeaders() {
    val grouped = names.sortedBy { it.first() }.groupBy { it.first() }
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        grouped.forEach { entry ->
            stickyHeader {
                Text(
                    text = entry.key.toString(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(start = 4.dp)
                )
            }
            items(entry.value) {
                Text(text = it)
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
