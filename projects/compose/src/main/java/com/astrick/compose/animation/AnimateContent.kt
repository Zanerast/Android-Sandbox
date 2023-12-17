package com.astrick.compose.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AnimateContentSample() {
    var number by remember { mutableIntStateOf(1) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (number == 1) {
                    number = 9
                } else {
                    number--
                }
            },
            content = {
                Image(imageVector = Icons.Filled.ArrowBackIos, contentDescription = null)
            },
            modifier = Modifier.size(40.dp)
        )
        AnimatedContent(
            targetState = number,
            label = "animatedContent",
            transitionSpec = {
                val direction = if ((targetState == 1 && initialState == 9) || targetState > initialState && (targetState != 9 || initialState != 1)) {
                    AnimatedContentTransitionScope.SlideDirection.Start
                } else {
                   AnimatedContentTransitionScope.SlideDirection.End
                }
                val enterTransition = slideIntoContainer(
                    towards = direction,
                    animationSpec = tween(durationMillis = 500)
                ) + fadeIn() + scaleIn()
    
                val exitTransition = slideOutOfContainer(
                    towards = direction,
                    animationSpec = tween(durationMillis = 500)
                ) + fadeOut() + scaleOut()
                
                enterTransition.togetherWith(exitTransition)
            },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = it.toString(),
                modifier = Modifier
                    .padding(horizontal = 40.dp)
            )
        }
        
        Button(
            onClick = {
                if (number == 9) {
                    number = 1
                } else {
                    number++
                }
            },
            content = {
                Image(imageVector = Icons.Filled.ArrowForwardIos, contentDescription = null)
            },
            modifier = Modifier.size(40.dp)
        )
    }
}

@Preview
@Composable
private fun MainPreview() {
    AnimateContentSample()
}
