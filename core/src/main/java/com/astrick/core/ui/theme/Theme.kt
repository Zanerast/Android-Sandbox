package com.astrick.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun SandboxTheme(
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
        LocalRippleTheme provides Ripple
    ) {
        content()
    }
}

object SandboxTheme {
    
    val color: CustomColor
        @Composable get() = LocalCustomColor.current
    
    val font: CustomFont
        @Composable get() = LocalCustomFont.current
    
    val shape: CustomShape
        @Composable get() = LocalCustomShape.current
    
}
