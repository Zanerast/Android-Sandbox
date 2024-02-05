package com.astrick.compose.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

private const val DEST_HOME = "nav_home"
private const val NAV_B = "nav_b"
private const val DEST_B_HOME = "route_b_home"
private const val DEST_B_A = "route_b_a"
private const val DEST_B_B = "route_b_b"

@Composable
fun NestedNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = DEST_HOME) {
        composable(DEST_HOME) {
            Template(title = "Home") {
                Button(
                    onClick = { navController.navigate(NAV_B) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Go to Screen B")
                }
            }
        }
        addNestedNavigation(navController)
    }
}

fun NavGraphBuilder.addNestedNavigation(
    navController: NavController,
    route: String = NAV_B
) {
    navigation(startDestination = DEST_B_HOME, route = route) {
        composable(DEST_B_HOME) {
            Template(title = "Route B Home") {
                Button(
                    onClick = { navController.navigate(DEST_B_A) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Go to Screen B-A")
                }
                Button(
                    onClick = { navController.navigate(DEST_B_B) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Go to Screen B-B")
                }
            }
        }
        composable(DEST_B_A) {
            Template(title = "Route B-A") {
                // Empty
            }
        }
        composable(DEST_B_B) {
            Template(title = "Route B-B") {
                // Empty
            }
        }
    }
}

@Composable
private fun Template(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(24.dp)
        )
        content()
    }
}
