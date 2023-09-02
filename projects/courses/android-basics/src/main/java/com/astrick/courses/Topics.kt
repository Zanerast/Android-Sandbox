package com.astrick.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.core.ui.theme.BaseComposeTheme
import com.astrick.courses.data.DataSource
import com.astrick.courses.data.Topic

@Composable
fun Topics(
    topics: List<Topic>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.background(Color.Blue).padding(4.dp)
        
    ) {
        items(topics.size) {
            Topic(
                topic = topics[it],
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

// Previews
@Preview(showSystemUi = true)
@Composable
fun TopicsPreview() {
    BaseComposeTheme {
        Topics(topics = DataSource.topics)
    }
}
