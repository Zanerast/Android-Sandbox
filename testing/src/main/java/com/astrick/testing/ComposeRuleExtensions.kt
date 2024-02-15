package com.astrick.testing

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.annotation.PluralsRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import junit.framework.TestCase.assertEquals

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int,
    vararg formatArgs: Any
): SemanticsNodeInteraction {
    val string = activity.getString(id, *formatArgs)
    return onNodeWithText(string)
}

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringPluralId(
    @PluralsRes id: Int,
    count: Int,
    vararg formatArgs: Any
): SemanticsNodeInteraction {
    val string = activity.resources.getQuantityString(id, count, *formatArgs)
    return onNodeWithText(string)
}

@RequiresApi(Build.VERSION_CODES.O)
fun SemanticsNodeInteraction.assertBackgroundColor(expectedBackground: Color) {
    val capturedName = captureToImage().colorSpace.name
    assertEquals(expectedBackground.colorSpace.name, capturedName)
}
