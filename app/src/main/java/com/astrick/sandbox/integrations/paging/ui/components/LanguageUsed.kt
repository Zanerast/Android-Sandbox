package com.astrick.sandbox.integrations.paging.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astrick.compose.R

/**
 * Displays the programming language used in a repository.
 *
 * @param languageUsed The name of the programming language to display.
 * @param modifier Modifier to be applied to this composable.
 */
@Composable
fun LanguageUsed(
    languageUsed: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.language, languageUsed),
        style = MaterialTheme.typography.labelMedium,
        modifier = modifier
            .padding(8.dp)
    )
}
