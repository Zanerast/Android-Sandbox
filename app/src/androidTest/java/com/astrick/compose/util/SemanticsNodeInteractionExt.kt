package com.astrick.compose.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import junit.framework.TestCase.assertEquals

@Suppress("unused")
object SemanticsNodeInteractionExt {

    /**
     * Asserts that the background color of the captured `SemanticsNodeInteraction` matches the expected color.
     *
     * This assertion compares the color space names of the captured image and the expected color.
     *
     * @param expectedBackground The expected background color to assert against.
     * @throws AssertionError if the color space names do not match.
     */
    fun SemanticsNodeInteraction.assertBackgroundColor(expectedBackground: Color) {
        val capturedName = captureToImage().colorSpace.name
        assertEquals(expectedBackground.colorSpace.name, capturedName)
    }
}

