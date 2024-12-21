package com.astrick.sandbox.integrations.paging.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ForkLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Displays the number of forks for a GitHub repo with an accompanying icon.
 *
 * @param forks The number of forks to display.
 * @param modifier Modifier to be applied to this composable.
 */
@Composable
fun GithubForks(
    forks: Int,
    modifier: Modifier = Modifier
) {
   Row(
       verticalAlignment = Alignment.CenterVertically,
       modifier = modifier
   ) {
       Icon(
           imageVector = Icons.Filled.ForkLeft,
           contentDescription = "Forks"
       )
       Text(
           text = forks.toString(),
           style = MaterialTheme.typography.labelMedium,
           modifier = Modifier
               .padding(8.dp)
       )
   }
}

@Preview
@Composable
private fun MainPreview() {
    GithubForks(60_000)
}
