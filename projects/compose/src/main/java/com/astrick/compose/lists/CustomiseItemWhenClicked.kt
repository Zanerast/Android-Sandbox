package com.astrick.compose.lists

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


private val names = listOf(
    "Abby", "John", "Emily", "Michael", "Sophia", "Daniel", "Emma", "Matthew", "Olivia", "William",
    "Ava", "James", "Isabella", "Alexander", "Mia", "Benjamin", "Charlotte", "Ethan", "Amelia",
    "Henry",
    "Liam", "Madison", "Noah", "Ella", "Logan", "Grace", "Lucas", "Chloe", "Jackson", "Avery",
    "Jack", "Sofia", "Elijah", "Lily", "Carter", "Hannah", "Mason", "Scarlett", "Evelyn", "Samuel"
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomiseItem() {
    val grouped = names.sortedBy { it.first() }.groupBy { it.first() }
    var selectedItem by remember { mutableStateOf("") }
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        grouped.forEach { entry ->
            stickyHeader {
                Text(
                    text = entry.key.toString(),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(start = 4.dp)
                
                )
            }
            items(entry.value) {
                val scale by animateFloatAsState(
                    targetValue = if (selectedItem == it) 1.5f else 1f,
                    label = ""
                )
                Text(
                    text = it,
                    modifier = Modifier
                        .clickable {
                            selectedItem = it
                        }
                        .scale(scale)
                )
            }
        }
    }
}