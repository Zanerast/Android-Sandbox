package com.astrick.compose.agnostic

import androidx.navigation.NavController
import junit.framework.TestCase.assertEquals

@Suppress("unused")
object NavControllerExt {
    /**
     * Verifies that the current route name of the [NavController] matches the expected route name.
     *
     * @param expectedRouteName the name of the route expected to be currently displayed.
     * @throws AssertionError if the current route name does not match the expected value.
     */
    fun NavController.assertCurrentRouteName(expectedRouteName: String) {
        val current = this.currentBackStackEntry?.destination?.route
        assertEquals(expectedRouteName, current)
    }
}
