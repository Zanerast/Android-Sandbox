package com.astrick.sandbox.integrations.paging.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Displays the description of a GitHub repository.
 *
 * @param description The description text to be displayed.
 * @param modifier Modifier to be applied to this composable.
 */
@Composable
fun GithubDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 5,
        modifier = modifier
    )
}
