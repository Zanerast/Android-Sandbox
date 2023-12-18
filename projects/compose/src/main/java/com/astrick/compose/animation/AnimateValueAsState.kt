package com.astrick.compose.animation

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class CardSize(val height: Dp, val width: Dp)

@Composable
fun AnimateValueAsStateSample() {

    var isPressed by remember { mutableStateOf(false) }
    val cardSize by animateValueAsState(
        targetValue = if (isPressed) CardSize(200.dp, 200.dp) else CardSize(250.dp, 250.dp),
        typeConverter = TwoWayConverter<CardSize, AnimationVector2D>(
            convertFromVector = { CardSize(it.v1.dp, it.v2.dp) },
            convertToVector = { AnimationVector2D(it.height.value, it.width.value) }
        ),
        label = "cardSizeAnimation"
    )

    Box(modifier = Modifier) {
        Card(
            backgroundColor = Color.Blue,
            modifier = Modifier
                .size(width = cardSize.width, height = cardSize.height)
                .align(Alignment.Center)
                .padding(12.dp)
                .clickable(
                    indication = null,
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        isPressed = !isPressed
                    }
                )
        ) {

        }
    }
}
