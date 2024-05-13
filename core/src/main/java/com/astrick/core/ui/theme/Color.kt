package com.astrick.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val BlueDark100 = Color(0xFF152238)
val PrimaryColor = Color.Blue

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
