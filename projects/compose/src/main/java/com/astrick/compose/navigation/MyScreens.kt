package com.astrick.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private const val DESTINATION_HOME = "home"
private const val DESTINATION_B = "route_b"
private const val DESTINATION_C = "route_c"
private const val DESTINATION_D = "route_d"
private const val DESTINATION_E = "route_e"

@Composable
fun MyScreens() {
    
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = DESTINATION_HOME) {
        composable(DESTINATION_HOME) {
            HomeScreen(navController)
        }
        composable(DESTINATION_B) {
            B_Screen()
        }
        composable(DESTINATION_C) {
            C_Screen(navController = navController)
        }
        composable(DESTINATION_D) {
            D_Screen()
        }
        composable(DESTINATION_E) {
            E_Screen()
        }
    }
    
}

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Button(onClick = { navController.navigate(DESTINATION_B) }) {
            Text(text = "Screen B")
        }
        
        Button(onClick = { navController.navigate(DESTINATION_C) }) {
            Text(text = "Screen C")
        }
        
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun B_Screen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Text(
            text = "Screen B",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun C_Screen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { navController.navigate(DESTINATION_D) }) {
            Text(text = "Screen D")
        }
        
        Button(onClick = { navController.navigate(DESTINATION_E) }) {
            Text(text = "Screen E")
        }
        
        Button(onClick = { navController.navigate(DESTINATION_HOME) }) {
            Text(text = "Home Screen")
        }
        
        Text(
            text = "Screen C",
            style = MaterialTheme.typography.h1
        )
    }
}

@Composable
fun D_Screen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Screen D",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Composable
fun E_Screen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Screen E",
            style = MaterialTheme.typography.h1,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}