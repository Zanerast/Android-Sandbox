package com.astrick.tipcalculator

import android.icu.text.NumberFormat
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.astrick.core.ui.theme.BaseComposeTheme

import org.junit.Test

import org.junit.Rule

class TipUITests {
    
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            BaseComposeTheme {
                TipTimeLayout()
            }
        }
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")
    
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }
}
