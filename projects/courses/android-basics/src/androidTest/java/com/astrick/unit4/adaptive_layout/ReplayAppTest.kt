package com.astrick.unit4.adaptive_layout

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.astrick.androidbasicscompose.R
import com.astrick.testing.onNodeWithTagForStringId
import com.astrick.unit4_adaptive_layout.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplayAppTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    
    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            ReplyApp(windowSize = WindowWidthSizeClass.Compact)
        }
        composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom)
            .assertExists()
    }
}
