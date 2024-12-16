package com.astrick.sandbox.compose.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A composable that demonstrates the animateValueAsState Compose function by
 * animating the size of a card when clicked.
 *
 * @modifier Modifier to customize the layout and appearance of the composable.
 */
@Composable
fun AnimateValueAsStateSample(modifier: Modifier = Modifier) {

    var isPressed by remember { mutableStateOf(false) }
    val cardSize by animateValueAsState(
        targetValue = if (isPressed) CardSize(200.dp, 200.dp) else CardSize(250.dp, 250.dp),
        typeConverter = TwoWayConverter<CardSize, AnimationVector2D>(
            convertFromVector = { CardSize(it.v1.dp, it.v2.dp) },
            convertToVector = { AnimationVector2D(it.height.value, it.width.value) }
        ),
        label = "cardSizeAnimation"
    )

    Box(modifier = modifier
        .size(width = cardSize.width, height = cardSize.height)
        .padding(12.dp)
        .background(Color.Blue)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
            onClick = {
                isPressed = !isPressed
            }
        )
    )
}

/**
 * Data class representing the size (height and width) of the card.
 *
 * @param height The height of the card.
 * @param width The width of the card.
 */
private data class CardSize(val height: Dp, val width: Dp)
