package com.astrick.compose.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AnimatablesSample(
    modifier: Modifier = Modifier
) {
    val (checked, onCheckedChanged) = remember { mutableStateOf(false) }

    val boxColor = remember { Animatable(Color.Red) }
    val boxHeight = remember { Animatable(200f) }

    LaunchedEffect(key1 = checked) {
        val targetColor = if (checked) Color.Yellow else Color.Red
        boxColor.animateTo(targetColor)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(boxHeight.value.dp)
                .padding(16.dp)
                .background(boxColor.value)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            coroutineScope {
                                awaitPointerEventScope {
                                    awaitFirstDown()
                                    launch {
                                        boxHeight.animateTo(400f)
                                    }
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
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChanged
        )
    }
}
