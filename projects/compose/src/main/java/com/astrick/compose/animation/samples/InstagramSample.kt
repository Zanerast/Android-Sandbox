package com.astrick.compose.animation.samples

import androidx.compose.animation.core.Spring
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
import com.astrick.compose.R

enum class Like {
    INITIAL,
    LIKED,
    GONE
}

@Composable
fun InstagramSample(
    modifier: Modifier = Modifier
) {
    var likeState by remember { mutableStateOf(Like.GONE) }
    val transition = updateTransition(
        targetState = likeState,
        label = "transition"
    )

    if (transition.currentState == Like.INITIAL) {
        likeState = Like.LIKED
    } else if (transition.currentState == Like.LIKED) {
        likeState = Like.GONE
    }

    val heartAlpha by transition.animateFloat(
        label = "heartAlpha",
        transitionSpec = {
            when (this.targetState) {
                Like.LIKED -> keyframes {
                    durationMillis = 500
                    0f at 0
                    0.5f at 225
                    1f at 400
                }

                Like.GONE -> tween(durationMillis = 300)
                else -> snap()
            }
        }
    ) {
        when (it) {
            Like.LIKED -> 1f
            else -> 0f
        }
    }

    val heartScale by transition.animateFloat(
        label = "heartScale",
        transitionSpec = {
            when (this.targetState) {
                Like.LIKED -> spring(dampingRatio = Spring.DampingRatioMediumBouncy)
                Like.GONE -> tween(durationMillis = 300)
                else -> snap()
            }
        },
        targetValueByState = {
            when (it) {
                Like.LIKED -> 3f
                else -> 0f
            }
        }
    )

    Box(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    likeState = Like.INITIAL
                }
            )
        }
    ) {

        Image(
            painter = painterResource(id = R.drawable.affirmations3),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
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
