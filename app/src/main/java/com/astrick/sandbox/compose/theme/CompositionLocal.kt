package com.astrick.sandbox.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/*
 * compositionLocalOf:
 * Changing the value provided during recomposition invalidates
 * only the content that reads its current value.
 *
 * A use-case might be: If your app supports user-selected themes (like light or dark mode) and
 * you want different parts of your UI to react to theme changes without rebuilding
 * the entire composable tree, you can use compositionLocalOf.
 */

/*
 * staticCompositionLocalOf:
 * Unlike compositionLocalOf, reads of a staticCompositionLocalOf are not tracked by Compose.
 * Changing the value causes the entirety of the content lambda where the
 * CompositionLocal is provided to be recomposed, instead of just the
 * places where the current value is read in the Composition.
 */

/*
 * The advantage to using CompositionLocal of a global variable is:
 * we can dynamically change the value based on conditions,
 * as shown where elevation changes based on the current theme (isSystemInDarkTheme())
 *
 * If the value provided to the CompositionLocal is highly unlikely to change or will never change,
 * use staticCompositionLocalOf to get performance benefits.
 *
 * Ref: developer.android.com/jetpack/compose/compositionlocal
 */

data class Elevations(val card: Dp = 0.dp, val default: Dp = 0.dp)

// Define a CompositionLocal global object with a default
// This instance can be accessed by all composables in the app
val LocalElevations = compositionLocalOf { Elevations() }

@Composable
fun CompositionLocalScreen() {
    val elevations = if (isSystemInDarkTheme()) {
        Elevations(card = 1.dp, default = 1.dp)
    } else {
        Elevations(card = 16.dp, default = 0.dp)
    }
    
    CompositionLocalProvider(LocalElevations provides elevations) {
        Column(modifier = Modifier.fillMaxSize()) {
            CompositionLocalContent()
        }
    }
}

@Composable
private fun CompositionLocalContent() {
    val cardElevation = LocalElevations.current.card
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation)
    ) {
        Text(
            text = "Hello",
            modifier = Modifier.padding(18.dp)
        )
    }
}
