package com.astrick.sandbox.compose.animation.complex

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.astrick.sandbox.app.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * A composable that demonstrates an interactive animation where a cupcake image moves
 * across the screen based on the user's click position.
 *
 * The user clicks anywhere on the screen, and the cupcake smoothly moves to that location.
 * The movement is animated using a spring-based animation for smooth transitions.
 *
 * @param modifier Modifier to customize the layout of the composable.
 */
@Composable
fun ClickToMoveTheCupcakeExample(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
        var size = IntSize(0, 0)
        var isClicked by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .onSizeChanged { size = it }
                .background(Color.Red)
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            val position = awaitPointerEventScope {
                                // Wait for the first down event (user clicks)
                                val pos = awaitFirstDown().position
                                isClicked = true
                                // Capture the position of the click
                                pos
                            }
                            // Animate cupcake movement
                            launch { offset.animateTo(position, spring()) }
                        }
                    }
                }
        ) {
            CupcakeText()
            CupcakeImage(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset {
                        // Adjust the cupcake position based on the click
                        val xAdjustment = if (isClicked) (size.width / 2) else 0
                        val yAdjustment = if (isClicked) (size.height / 2) else 0
                        val x = offset.value.x.toInt() - xAdjustment
                        val y = offset.value.y.toInt() - yAdjustment
                        IntOffset(x, y)
                    }
            )
        }
    }
}

@Composable
private fun CupcakeText(modifier: Modifier = Modifier) {
    Text(
        text = "Click to move the cupcake",
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
    )
}

@Composable
private fun CupcakeImage(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.cupcake),
        contentDescription = null,
        modifier = modifier
            .size(50.dp)
            .padding(8.dp)
    )
}
