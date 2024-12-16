package com.astrick.sandbox.compose.animation

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
* A composable that demonstrates the AnimatedContent Compose function in a counter-like UI.
*
* The counter starts at 1 and can be incremented or decremented by clicking the respective buttons.
* - The `MinusButton` decreases the number and wraps from 1 to 9.
* - The `PlusButton` increases the number and wraps from 9 to 1.
*
* @param modifier Modifier to customize the layout and appearance of the composable.
*/
@Composable
fun AnimateContentSample(modifier: Modifier = Modifier) {
    var number by remember { mutableIntStateOf(1) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        MinusButton {
            if (number == 1)
                number = 9
            else
                number--
        }

        NumberText(
            number = number,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        PlusButton {
            if (number == 9)
                number = 1
            else
                number++
        }
    }
}

@Composable
private fun MinusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(40.dp),
    ) {
        Image(imageVector = Icons.Filled.KeyboardDoubleArrowLeft, contentDescription = null)
    }
}

@Composable
private fun NumberText(
    number: Int,
    modifier: Modifier = Modifier
) {
    AnimatedContent(
        targetState = number,
        label = "animatedContent",
        transitionSpec = {
            val direction =
                if ((targetState == 1 && initialState == 9) || targetState > initialState && (targetState != 9 || initialState != 1)) {
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
        modifier = modifier
    ) {
        Text(
            text = it.toString(),
            modifier = Modifier
                .padding(horizontal = 40.dp)
        )
    }
}

@Composable
private fun PlusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(40.dp),
    ) {
        Image(imageVector = Icons.Filled.KeyboardDoubleArrowRight, contentDescription = null)
    }
}

@Preview
@Composable
private fun MainPreview() {
    AnimateContentSample()
}
