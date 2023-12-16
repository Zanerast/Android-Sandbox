package com.astrick.compose.animation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Face2
import androidx.compose.material.icons.filled.Face3
import androidx.compose.material.icons.filled.Face4
import androidx.compose.material.icons.filled.Face5
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CrossFadeSample() {
    val images = listOf(
        Icons.Filled.Face,
        Icons.Filled.Face2,
        Icons.Filled.Face3,
        Icons.Filled.Face4,
        Icons.Filled.Face5,
    )
    var selectedImage by remember { mutableStateOf(images[0]) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .clickable(
                onClick = {
                    var newImage = images.random()
                    while (newImage == selectedImage) {
                        newImage = images.random()
                    }
                    selectedImage = newImage
                },
                indication = null, // used to remove ripple
                interactionSource = MutableInteractionSource()
            )
    ) {
        Crossfade(
            targetState = selectedImage,
            label = "crossFade",
            animationSpec = tween(
                durationMillis = 1_000
            )
        ) {
            Image(imageVector = it, contentDescription = null, modifier = Modifier.fillMaxSize())
        }
    }
}
