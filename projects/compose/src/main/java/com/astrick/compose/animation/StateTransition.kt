package com.astrick.compose.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp

enum class States {
    STATE_ONE,
    STATE_TWO,
    STATE_THREE
}

@Composable
fun StateTransitionSample() {

    var state by remember { mutableStateOf(States.STATE_ONE) }
    val transition = updateTransition(
        targetState = state,
        label = "transition"
    )
    val color by transition.animateColor(label = "colorTransition") {
        when (it) {
            States.STATE_ONE -> Color.Green
            States.STATE_TWO -> Color.Blue
            States.STATE_THREE -> Color.Red
        }
    }
    val cornerSize by transition.animateDp(label = "cornerSizeTransition") {
        when (it) {
            States.STATE_ONE -> 12.dp
            States.STATE_TWO -> 0.dp
            States.STATE_THREE -> 40.dp
        }
    }
    val textSize by transition.animateFloat(label = "textSizeTransition") {
        when (it) {
            States.STATE_ONE -> 20f
            States.STATE_TWO -> 50f
            States.STATE_THREE -> 30f
        }
    }
    val text = when (state) {
        States.STATE_ONE -> "State One"
        States.STATE_TWO -> "State Two"
        States.STATE_THREE -> "State Three"
    }
    Box(modifier = Modifier
        .background(color = color, shape = RoundedCornerShape(cornerSize))
        .clickable {
            state = when(state) {
                States.STATE_ONE -> States.STATE_TWO
                States.STATE_TWO -> States.STATE_THREE
                States.STATE_THREE -> States.STATE_ONE
            }
        }
    ) {
        Text(
            text = text,
            fontSize = TextUnit(textSize, TextUnitType.Sp),
            modifier = Modifier.padding(8.dp)
        )
    }
}
