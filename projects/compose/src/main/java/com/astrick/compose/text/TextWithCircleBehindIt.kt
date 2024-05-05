package com.astrick.compose.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextWithCircleBehindIt() {
    Text(
        modifier = Modifier
            .padding(16.dp)
            .drawBehind {
                drawCircle(
                    color = Color.Red,
                    radius = size.maxDimension
                )
            }
            .padding(4.dp) // increase this to increase the diameter of the circus
        ,
        text = "Hello",
    )
}
