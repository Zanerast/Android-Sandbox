package com.astrick.compose.animation

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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun InfiniteTransitionSample(
    modifier: Modifier = Modifier
) {

    val transition = rememberInfiniteTransition(label = "Infinite Transition")
    val lineStart by transition.animateFloat(
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


                drawLine(
                    color = Color.Blue,
                    start = Offset(lineStart, size.height / 2),
                    end = Offset(lineStart + lineWidth, size.height / 2),
                    strokeWidth = 5f
                )
            }
        )
    }
}

@Composable
fun InfiniteTransitionSampleTwo(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val pitch by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pitchAnimation"
    )
    val yaw by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 90f,
        animationSpec = infiniteRepeatable(
            animation = tween(1_000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "yawAnimation"
    )

    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(45f)
                .graphicsLayer {
                    rotationX = pitch
                },
            onDraw = {
                drawCircle(
                    color = Color.Blue,
                    style = Stroke(5f)
                )
            }
        )
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(45f)
                .graphicsLayer {
                    rotationY = yaw
                },
            onDraw = {
                drawCircle(
                    color = Color.Blue,
                    style = Stroke(5f)
                )
            }
        )
    }
}
