package com.astrick.sandbox.compose.animation

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

/**
 * A composable that demonstrates Infinite Transition by animating a line's position.
 *
 * @param modifier Modifier to be applied to the Box container.
 */
@Composable
fun InfiniteTransitionSample(
    modifier: Modifier = Modifier
) {

    val transition = rememberInfiniteTransition(label = "Infinite Transition")
    val lineStartingPosition by transition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Line Start"
    )
    Box(
        modifier = modifier
    ) {
        Canvas(
            modifier = Modifier.fillMaxSize(),
            onDraw = {
                val lineWidth = (size.width / 3)

                // Line that moves horizontally based on lineStart
                drawLine(
                    color = Color.Blue,
                    start = Offset(lineStartingPosition, size.height / 2),
                    end = Offset(lineStartingPosition + lineWidth, size.height / 2),
                    strokeWidth = 5f
                )
            }
        )
    }
}
