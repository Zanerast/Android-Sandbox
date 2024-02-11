package com.astrick.compose.navigation

import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private const val DESTINATION_HOME = "destination_home"
private const val DESTINATION_SECOND = "destination_second"

@Composable
fun NavTransitions() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DESTINATION_HOME) {
        composable(
            route = DESTINATION_HOME,
            popEnterTransition = {
                expandIn()
            }
        ) {
            Template(
                name = "Home", buttonText = "Go to screen 2", backgroundColor = Color.Yellow
            ) {
                navController.navigate(DESTINATION_SECOND)
            }
        }
        composable(
            route = DESTINATION_SECOND,
            enterTransition = {
                fadeIn() + expandVertically()
            },
            exitTransition = {
                fadeOut() + shrinkVertically()
            },
            popExitTransition = {
                fadeOut() + slideOutHorizontally()
            }
        ) {
            Template(
                name = "Second Screen", buttonText = "Go to Home", backgroundColor = Color.Blue
            ) {
                navController.navigate(DESTINATION_HOME)
            }
        }
    }
}

@Composable
private fun Template(
    name: String,
    buttonText: String,
    backgroundColor: Color,
    onClickNav: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
    ) {
        Text(
            text = name,
            fontSize = 28.sp,
            modifier = Modifier.padding(8.dp)
        )
        Button(onClick = onClickNav) {
            Text(text = buttonText)
        }
    }
}
