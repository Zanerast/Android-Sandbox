package com.astrick.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.astrick.compose.R

// More info here:
// https://developer.android.com/jetpack/compose/navigation#bottom-nav

private const val DEST_HOME = "dest_home"
private const val DEST_PHONE = "dest_phone"
private const val DEST_EMAIL = "dest_email"

sealed class BottomNavScreens(
    val route: String,
    @StringRes val titleId: Int,
    @DrawableRes val imageId: Int
) {
    object Home :
        BottomNavScreens(DEST_HOME, R.string.bottom_nav_home_title, R.drawable.ic_android)

    object Email :
        BottomNavScreens(DEST_EMAIL, R.string.bottom_nav_email_title, R.drawable.ic_email)

    object Phone :
        BottomNavScreens(DEST_PHONE, R.string.bottom_nav_phone_title, R.drawable.ic_phone)
}

@Composable
fun BottomNavigationScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = { MyTopBar() },
        bottomBar = { MyBottomBar(navController) }
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
            addNestedNavigation(navController, route = BottomNavScreens.Phone.route)
        }
    }

}

@Composable
private fun TemplateScreen(
    title: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = title,
            fontSize = 46.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(24.dp)
        )
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyTopBar() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.bottom_nav_top_bar_title)) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Cyan)
    )
}

@Composable
private fun MyBottomBar(navController: NavController) {
    val items = listOf(BottomNavScreens.Email, BottomNavScreens.Home, BottomNavScreens.Phone)

    BottomNavigation(
        backgroundColor = Color.Cyan
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            // There is also NavigationBarItem which has a diff UI look
            val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
            BottomNavigationItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = screen.imageId),
                        contentDescription = stringResource(id = screen.titleId)
                    )
                },
                label = {
                    // Updating color here manually since BottomNavigationItem doesn't want
                    // to update the color itself
                    val color = if (isSelected) Color.White else Color.Gray
                    Text(
                        text = stringResource(id = screen.titleId),
                        color = color
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,

            )
        }
    }
}
