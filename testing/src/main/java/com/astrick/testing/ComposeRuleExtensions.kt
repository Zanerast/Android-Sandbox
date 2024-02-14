package com.astrick.testing

import androidx.activity.ComponentActivity
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

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
