package com.astrick.sandbox.compose.navigation.bottom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.astrick.sandbox.compose.navigation.bottom.components.BottomBarForBottomNavigationExample
import com.astrick.sandbox.compose.navigation.bottom.components.TemplateScreen
import com.astrick.sandbox.compose.navigation.bottom.model.BottomNavScreens

/**
 * Composable that displays a screen with a bottom navigation bar.
 *
 * Sets up a `Scaffold` with a bottom bar. It uses a `NavController` to manage
 * navigation between different screens.
 *
 * Reference: developer.android.com/jetpack/compose/navigation#bottom-nav
 *
 * @see NavHost for managing navigation between screens.
 */
@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBarForBottomNavigationExample(navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavScreens.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(BottomNavScreens.Home.route) {
                TemplateScreen(title = "Home Screen")
            }
            composable(BottomNavScreens.Email.route) {
                TemplateScreen(title = "Email Screen")
            }
            composable(BottomNavScreens.Phone.route) {
                TemplateScreen(title = "Phone Screen")
            }
        }
    }
}
