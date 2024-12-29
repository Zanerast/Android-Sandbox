package com.astrick.sandbox.compose.animation.complex

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.snap
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
        var size: IntSize
        val scope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .onSizeChanged {
                    size = it
                    scope.launch {
                        // Center cupcake
                        offset.animateTo(Offset(size.width / 2f, size.height / 2f), snap())
                    }
                }
                .background(Color.Red)
                .pointerInput(Unit) {
                    coroutineScope {
                        while (true) {
                            val position = awaitPointerEventScope {
                                // Wait for the first down event (user clicks)
                                val pos = awaitFirstDown().position
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
                    .size(50.dp)
                    .offset {
                        val xPos = offset.value.x - 25.dp.toPx()
                        val yPos = offset.value.y - 25.dp.toPx()
                        IntOffset(xPos.toInt(), yPos.toInt())
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
    )
}

@Preview
@Composable
private fun MainPreview() {
    ClickToMoveTheCupcakeExample()
}
