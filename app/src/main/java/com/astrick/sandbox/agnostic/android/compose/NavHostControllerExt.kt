package com.astrick.sandbox.agnostic.android.compose

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

@Suppress("MemberVisibilityCanBePrivate", "unused")
object NavHostControllerExt {

    /**
     * Navigates to the given route, ensuring that only a single instance of the destination is on the back stack.
     * If the destination already exists in the back stack, it restores its state instead of creating a new instance.
     *
     * @param route The route to navigate to.
     */
    fun NavHostController.navigateSingleTopTo(route: String) {
        this.navigate(route) {
            popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

}
