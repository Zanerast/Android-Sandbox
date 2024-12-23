package com.astrick.sandbox.compose.animation.complex

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.astrick.sandbox.app.R
import com.astrick.sandbox.compose.animation.complex.Like.GONE
import com.astrick.sandbox.compose.animation.complex.Like.INITIAL
import com.astrick.sandbox.compose.animation.complex.Like.LIKED

/**
 * A composable function that mimics Instagram's double-tap heart animation effect.
 *
 * The function animates a heart icon when the user double-taps the screen.
 * The heart grows in size and fades in when liked, then fades out and disappears.
 *
 * @param modifier The [Modifier] applied to the root composable.
 */
@Composable
fun InstagramHeartExample(
    modifier: Modifier = Modifier
) {
    var likeState by remember { mutableStateOf(GONE) }
    val transition = updateTransition(
        targetState = likeState,
        label = "transition"
    )

    if (transition.currentState == INITIAL)
        likeState = LIKED
    else if (transition.currentState == LIKED)
        likeState = GONE

    val heartAlpha by transition.animateFloat(
        label = "heartAlpha",
        transitionSpec = alphaTransitionSpec()
    ) {
        when (it) {
            LIKED -> 1f
            else -> 0f
        }
    }

    val heartScale by transition.animateFloat(
        label = "heartScale",
        transitionSpec = scaleTransitionSpec(),
        targetValueByState = {
            when (it) {
                LIKED -> 3f
                else -> 0f
            }
        }
    )

    Box(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    likeState = INITIAL
                }
            )
        }
    ) {
        Background()
        HeartIcon(modifier = Modifier
            .size(100.dp)
            .align(Alignment.Center)
            .graphicsLayer {
                alpha = heartAlpha
                scaleX = heartScale
                scaleY = heartScale
            }
        )
    }
}

@Composable
private fun alphaTransitionSpec(): @Composable() (Transition.Segment<Like>.() -> FiniteAnimationSpec<Float>) = {
    when (this.targetState) {
        LIKED -> keyframes {
            durationMillis = 500
            0f at 0
            0.5f at 225
            1f at 400
        }

        GONE -> tween(durationMillis = 300)
        else -> snap()
    }
}

@Composable
private fun scaleTransitionSpec(): @Composable() (Transition.Segment<Like>.() -> FiniteAnimationSpec<Float>) = {
    when (this.targetState) {
        LIKED -> spring(dampingRatio = Spring.DampingRatioMediumBouncy)
        GONE -> tween(durationMillis = 300)
        else -> snap()
    }
}

@Composable
private fun HeartIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = null,
        tint = Color.White,
        modifier = modifier
    )
}

@Composable
private fun Background() {
    Image(
        painter = painterResource(id = R.drawable.affirmations3),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
    )
}

/**
 * Represents the state of the heart animation.
 * - [INITIAL]: The heart animation is triggered.
 * - [LIKED]: The heart is fully visible and scaled.
 * - [GONE]: The heart disappears after the animation completes.
 */
private enum class Like {
    INITIAL,
    LIKED,
    GONE
}
