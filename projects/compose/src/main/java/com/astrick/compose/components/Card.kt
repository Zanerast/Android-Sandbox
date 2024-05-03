package com.astrick.compose.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.astrick.compose.theming.custom.CustomTheme

@Composable
fun ExampleCard(
    modifier: Modifier = Modifier,
    shape: Shape = CustomTheme.shape.buttonShape,
    content: @Composable () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        modifier = modifier,
        shape = shape
    ) {
        content()
    }
}
