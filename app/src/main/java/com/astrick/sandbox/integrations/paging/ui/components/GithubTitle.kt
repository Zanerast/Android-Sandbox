package com.astrick.sandbox.integrations.paging.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Displays the title of a GitHub repo with a large blue text style.
 *
 * @param text The title text to display.
 * @param modifier Modifier to be applied to this composable.
 */
@Composable
fun GithubTitle(
    text: String,
    modifier: Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = Color.Blue,
        modifier = modifier,
    )
}
