package com.astrick.testing

import androidx.navigation.NavController
import junit.framework.TestCase.assertEquals

fun NavController.assertCurrentRouteName(expectedRouteName: String) {
    val current = this.currentBackStackEntry?.destination?.route
    assertEquals(expectedRouteName, current)
}
