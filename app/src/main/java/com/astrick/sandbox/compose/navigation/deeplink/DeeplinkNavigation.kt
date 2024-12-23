package com.astrick.sandbox.compose.navigation.deeplink

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink

private const val DEEPLINK_GREETING = "destination_greeting"

/**
 * Deeplink can be tested with:
 * adb shell am start -a android.intent.action.VIEW -d "deeplink://greeting/zane"
 *
 * adb = Android Debug Bridge
 * shell = tells it we want to execute a shell(text based interface) command
 * am = Activity Manager
 * start = sub-command of am that allows us to start the activity
 * -d = deeplink
 * -a android.intent.action.VIEW = sets the intent action to use
 * - Note: This isn't required if we have the DEFAULT category set on the intent
 */
@Composable
fun DeeplinkNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DEEPLINK_GREETING) {
        composable(
            route = DEEPLINK_GREETING,
            deepLinks = listOf(navDeepLink { uriPattern = "deeplink://greeting/{name}" })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            DeepLinkGreetingScreen(name)
        }
    }
}

@Composable
private fun DeepLinkGreetingScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        val message = if (name.isEmpty()) {
            "Looks like the deeplink failed"
        } else {
            "Hello $name. Congrats on the deeplink"
        }
        Text(
            text = message,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
