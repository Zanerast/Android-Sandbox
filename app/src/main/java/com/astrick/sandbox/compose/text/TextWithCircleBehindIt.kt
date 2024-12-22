package com.astrick.sandbox.compose.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Displays a text with a circular background drawn behind it.
 *
 * @param modifier The modifier to be applied to the text and its background.
 */
@Composable
fun TextWithCircleBehindIt(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier
            .padding(16.dp)
            .drawBehind {
                drawCircle(
                    color = Color.Red,
                    radius = size.maxDimension / 2 // Ensures circle fits within bounds
                )
            }
            .padding(8.dp)
        ,
        text = "5",
    )
}

@Preview
@Composable
private fun MainPreview() {
    TextWithCircleBehindIt()
}