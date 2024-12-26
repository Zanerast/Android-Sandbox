package com.astrick.compose.agnostic

import androidx.activity.ComponentActivity
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

@Suppress("unused")
object AndroidComposeTestRuleExt {

    /**
     * Finds a node in the Compose tree with the given string resource ID.
     *
     * @param id The resource ID of the string.
     * @param formatArgs Optional formatting arguments for the string.
     * @return The `SemanticsNodeInteraction` for the matching node.
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
        @StringRes id: Int,
        vararg formatArgs: Any
    ): SemanticsNodeInteraction {
        val string = activity.getString(id, *formatArgs)
        return onNodeWithText(string)
    }


    /**
     * Finds a node in the Compose tree with the given plural string resource ID.
     *
     * @param id The resource ID of the plural string.
     * @param count The quantity to resolve the plural string.
     * @param formatArgs Optional formatting arguments for the plural string.
     * @return The `SemanticsNodeInteraction` for the matching node.
     */
    fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringPluralId(
        @PluralsRes id: Int,
        count: Int,
        vararg formatArgs: Any
    ): SemanticsNodeInteraction {
        val string = activity.resources.getQuantityString(id, count, *formatArgs)
        return onNodeWithText(string)
    }


}
