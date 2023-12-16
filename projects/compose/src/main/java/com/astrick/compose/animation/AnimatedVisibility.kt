package com.astrick.compose.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilitySample() {
    val (checked, onCheckChanged) = remember { mutableStateOf(true) }
    
    val show = remember {
        MutableTransitionState(false).apply { targetState = true }
    }
    
    Card(
        modifier = Modifier
            .padding(16.dp)
            .shadow(5.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row {
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckChanged
                )
                Text(
                    text = "Make Visible",
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            
            AnimatedVisibility(
                visible = checked,
//                visibleState = show,
                enter = slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                    )
                ),
                exit = shrinkOut(
                            shrinkTowards = Alignment.TopStart,
                            targetSize =  {
                                val height = it.height / 2
                                IntSize(height, height)
                            },
                            animationSpec = tween(durationMillis = 1_000)
                        ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                        .aspectRatio(1f)
                        .background(Color.Gray)
                ) {
                    Image(
                        imageVector = Icons.Filled.Face,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .animateEnterExit(
                                exit = shrinkOut(
                                    shrinkTowards = Alignment.TopStart,
                                    targetSize = {
                                        val size = it.height / 4
                                        IntSize(size, size)
                                    },
                                    animationSpec = tween(durationMillis = 1_000)
                                )
                            )
                    )
                    Text(
                        text = "Sample Text",
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .animateEnterExit(
                                enter = slideInHorizontally { it * 2 },
                                exit = slideOutHorizontally { it * 2 }
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    AnimatedVisibilitySample()
}
