package com.astrick.compose.theming.custom

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColor(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val error: Color
)

val LocalCustomColor = staticCompositionLocalOf {
    CustomColor(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        secondary = Color.Unspecified,
        onSecondary = Color.Unspecified,
        error = Color.Unspecified
    )
}

fun getCustomColors(): CustomColor {
    return CustomColor(
        primary = Color.Blue,
        onPrimary = Color.White,
        secondary = Color.Yellow,
        onSecondary = Color.White,
        error = Color.Red
    )
}
