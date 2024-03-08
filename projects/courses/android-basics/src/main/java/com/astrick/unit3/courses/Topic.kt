package com.astrick.unit3.courses

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astrick.androidbasicscompose.R
import com.astrick.core.ui.theme.SandboxTheme
import com.astrick.unit3.courses.data.DataSource
import com.astrick.unit3.courses.data.Topic

@Composable
fun Topic(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(68.dp)
    ) {
        Row {
            Image(
                painter = painterResource(id = topic.imageResId),
                contentDescription = stringResource(id = topic.titleResId),
                modifier = Modifier.size(68.dp)
            )
            Column(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp
                )
            ) {
                // Topic
                Text(text = stringResource(id = topic.titleResId), style = MaterialTheme.typography.bodyMedium)
                // Courses
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "",
                    )
                    Text(
                        text = topic.numberOfAssociatedCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
            
        }
    }
}

@Preview
@Composable
fun TopicArchitecturePreview() {
    SandboxTheme {
        Topic(DataSource.topics.first())
    }
}

@Preview
@Composable
fun TopicCraftsPreview() {
    SandboxTheme {
        Topic(DataSource.topics[1])
    }
}
