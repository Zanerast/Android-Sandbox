package com.astrick.core.ui.theme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Ripple : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return Color.Yellow
    }
    
    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleAlpha(
            draggedAlpha = 0.2f,
            focusedAlpha = 0.4f,
            hoveredAlpha = 0.6f,
            pressedAlpha = 1f
        )
    }
}
