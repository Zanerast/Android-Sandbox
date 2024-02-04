package com.astrick.compose.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

private const val DEEPLINK_HOME = "destination_home"
private const val DEEPLINK_GREETING = "destination_greeting"

/**
 * Remember to also add to the manifest
 *
 * Deeplink can be tested with:
 * adb shell am start -W -a android.intent.action.VIEW -d "mydeeplinkapp://greeting/zane"
 *
 * adb = Android Debug Bridge
 * shell = tells it we want to execute a shell(text based interface) command
 * am = Activity Manager
 * start = sub-command of am that allows us to start the activity
 * -W = wait for activity to finish before displaying command prompt again (so not actually needed)
 * -d = deeplink
 * -a android.intent.action.VIEW = sets the intent action to use
 * - Note: This isn't required if we have the DEFAULT category set on the intent
 *
 * Can also use this in an html file:
 * <!DOCTYPE html>
 *     <html>
 *         <head>
 *             <title>Deep Link</title>
 *         </head>
 *         <body>
 *             <a href="mydeeplinkapp://greeting/zane">Click here</a>
 *         </body>
 *     </html>
 */
@Composable
fun NavWithDeeplink() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DEEPLINK_HOME) {
        composable(DEEPLINK_HOME) {
            DeepLinkHomeScreen()
        }
        composable(
            route = DEEPLINK_GREETING,
            deepLinks = listOf(navDeepLink { uriPattern = "mydeeplinkapp://greeting/{name}" })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            DeepLinkGreetingScreen(name)
        }
    }
}

@Composable
fun DeepLinkHomeScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Welcome to Home screen",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun DeepLinkGreetingScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Hello $name",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
