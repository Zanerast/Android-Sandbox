package com.astrick.compose.theming.custom

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun CustomTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    
    // Used for text input and cursor color
    val textSelectionColors = TextSelectionColors(
        handleColor = Color.Green,
        backgroundColor = Color.Magenta
    )
    CompositionLocalProvider(
        LocalCustomColor provides getCustomColors(),
        LocalCustomFont provides getCustomFonts(),
        LocalCustomShape provides getCustomShape(),
        LocalTextSelectionColors provides textSelectionColors,
        LocalRippleTheme provides CustomRipple
    ) {
        content()
    }
}

object CustomTheme {
    
    val color: CustomColor
        @Composable get() = LocalCustomColor.current
    
    val font: CustomFont
        @Composable get() = LocalCustomFont.current
    
    val shape: CustomShape
        @Composable get() = LocalCustomShape.current
    
}
