package com.astrick.sandbox.compose.animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/**
 * An example composable demonstrating the use of `pointerInput` and `detectTapGestures`
 * to animate the height of a box when it is tapped.
 *
 * @param modifier Modifier to customize the layout and appearance of the composable.
 */
@Composable
fun PointerInputDetectTapGestureSample(
    modifier: Modifier = Modifier
) {
    val boxHeight = remember { Animatable(200f) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(boxHeight.value.dp)
            .padding(16.dp)
            .background(Color.Red)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        coroutineScope.launch {
                            awaitPointerEventScope {
                                awaitFirstDown()
                                launch {
                                    boxHeight.animateTo(400f)
                                }
                                // Wait for gesture to end
                                waitForUpOrCancellation()
                                launch {
                                    boxHeight.animateTo(
                                        targetValue = 200f,
                                        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                                    )
                                }
                            }
                        }
                    }
                )
            }
    )
}
