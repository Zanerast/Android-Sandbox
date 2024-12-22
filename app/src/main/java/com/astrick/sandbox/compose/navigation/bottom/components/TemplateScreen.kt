package com.astrick.sandbox.compose.navigation.bottom.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable that displays a simple screen with a centered title.
 *
 * @param title The text to be displayed in the center of the screen.
 * @param modifier A [Modifier] to be applied to the `Box`. Defaults to `Modifier.fillMaxSize()`.
 */
@Composable
fun TemplateScreen(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = title,
            fontSize = 46.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        )
    }
}
