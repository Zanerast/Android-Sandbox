package com.astrick.compose.theming.custom

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Immutable
data class CustomShape(
    val buttonShape: Shape,
    val listItemShape: Shape
)

val LocalCustomShape = staticCompositionLocalOf {
    CustomShape(
        buttonShape = RectangleShape,
        listItemShape = RectangleShape
    )
}

fun getCustomShape(): CustomShape {
    return CustomShape(
        buttonShape = RoundedCornerShape(12.dp),
        listItemShape = CutCornerShape(8.dp)
    )
}
