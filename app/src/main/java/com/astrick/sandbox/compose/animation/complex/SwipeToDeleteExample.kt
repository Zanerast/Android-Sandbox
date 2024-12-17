package com.astrick.sandbox.compose.animation.complex

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.spring
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.horizontalDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

/**
 * A composable that demonstrates a swipe-to-dismiss interaction.
 *
 * Displays a text that can be swiped horizontally to dismiss.
 * When swiped, a "Dismissed" toast is shown.
 *
 * @param modifier Modifier to customize the layout and styling.
 */
@Composable
fun SwipeToDeleteExample(
    modifier: Modifier = Modifier
) {

    var dismissed by remember { mutableStateOf(false) }
    val context = LocalContext.current

    if (dismissed)
        Toast.makeText(context, "Dismissed", Toast.LENGTH_SHORT).show()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        AnimatedVisibility(visible = !dismissed) {

        }
    }
    SwipeToDeleteText {
        dismissed = true
    }
}

@Composable
private fun SwipeToDeleteText(
    modifier: Modifier = Modifier,
    onSwipeToDelete: () -> Unit
) {
    Text(
        text = "Swipe to dismiss",
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .swipeToDelete { onSwipeToDelete() }
            .background(Color.LightGray)
            .border(2.dp, Color.Red, RoundedCornerShape(5.dp))

    )
}

/*
 * Modifier extension to enable swipe-to-delete behavior.
 *
 * Handles the swipe gesture, animates the content's horizontal movement, and triggers the provided
 * callback when the swipe threshold is passed.
 *
 * @param onDismissed Callback to trigger when the item is dismissed.
 */
private fun Modifier.swipeToDelete(onDismissed: () -> Unit): Modifier = composed {
    val offsetX = remember { Animatable(0f) }
    pointerInput(Unit) {
        /*
        Decay makes the swipe action feel more realistic by allowing the item to continue sliding
        smoothly after the user's finger is lifted, gradually slowing down instead of stopping suddenly.
         */
        val decay = splineBasedDecay<Float>(this)
        coroutineScope {
            val velocityTracker = VelocityTracker()
            while (true) {
                // Track the swipe starting point
                val pointerId = awaitPointerEventScope { awaitFirstDown().id }
                launch { offsetX.stop() }
                awaitPointerEventScope {
                    // Code inside here runs while the user is dragging
                    horizontalDrag(pointerId) { change ->
                        launch {
                            // Update horizontal offset as the user swipes
                            offsetX.snapTo(offsetX.value + change.positionChange().x)
                        }
                        velocityTracker.addPosition(change.uptimeMillis, change.position)
                    }
                }
                // Calculate the velocity of the swipe
                val velocity = velocityTracker.calculateVelocity().x
                val targetOffsetX = decay.calculateTargetValue(offsetX.value, velocity)

                // Ensure the offset stays within bounds
                offsetX.updateBounds(
                    lowerBound = -size.width.toFloat(),
                    upperBound = size.width.toFloat()
                )
                launch {
                    // If the swipe distance exceeds 75% of the item width, trigger the dismissal
                    if (targetOffsetX.absoluteValue > 0.75 * size.width) {
                        offsetX.animateDecay(velocity, decay)
                        onDismissed()
                    } else {
                        // Otherwise, return the item to its original position
                        offsetX.animateTo(
                            0f,
                            spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                        )
                    }
                }
            }
        }
    }.offset {
        IntOffset(offsetX.value.toInt(), 0)
    }
}
