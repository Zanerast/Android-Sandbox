package com.astrick.compose.navigation

import androidx.compose.animation.expandIn
import androidx.compose.animation.slideInVertically
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
private const val DESTINATION_THIRD = "destination_third"

@Composable
fun NavTransitions() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DESTINATION_HOME,
        enterTransition = {
            slideInVertically()
        }
    ) {
        composable(
            route = DESTINATION_HOME,
            popEnterTransition = {
                expandIn()
            }
        ) {
            Template(
                name = "Home", backgroundColor = Color.Yellow
            ) {
                Button(onClick = { navController.navigate(DESTINATION_SECOND) }) {
                    Text(text = "Go to screen 2")
                }
                Button(onClick = { navController.navigate(DESTINATION_THIRD) }) {
                    Text(text = "Go to screen 3")
                }
            }
        }
        composable(
            route = DESTINATION_SECOND
        ) {
            Template(
                name = "Second Screen", backgroundColor = Color.Blue
            ) {
                
                Button(onClick = { navController.navigate(DESTINATION_HOME) }) {
                    Text(text = "Go to Home")
                }
                Button(onClick = { navController.navigate(DESTINATION_THIRD) }) {
                    Text(text = "Go to screen 3")
                }
            }
        }
        composable(
            route = DESTINATION_THIRD,
            enterTransition = {
                when (initialState.destination.route) {
                    DESTINATION_HOME -> {
                        expandIn()
                    }
                    else -> {
                        // null means it will default to the parent enterTransition
                        // which is slideInVertically on line 33
                        null
                    }
                }
            }
        ) {
            Template(
                name = "Third Screen", backgroundColor = Color.Red
            ) {
                
                Button(onClick = { navController.navigate(DESTINATION_HOME) }) {
                    Text(text = "Go to Home")
                }
                Button(onClick = { navController.navigate(DESTINATION_SECOND) }) {
                    Text(text = "Go to screen 2")
                }
            }
        }
    }
}

@Composable
private fun Template(
    name: String,
    backgroundColor: Color,
    content: @Composable () -> Unit
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
        
        content()
    }
}
