package com.astrick.compose.navigation.samples.lunch_tray

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.astrick.compose.R
import com.astrick.compose.navigation.samples.lunch_tray.data.DataSource
import com.astrick.compose.navigation.samples.lunch_tray.ui.AccompanimentMenuScreen
import com.astrick.compose.navigation.samples.lunch_tray.ui.CheckoutScreen
import com.astrick.compose.navigation.samples.lunch_tray.ui.EntreeMenuScreen
import com.astrick.compose.navigation.samples.lunch_tray.ui.OrderViewModel
import com.astrick.compose.navigation.samples.lunch_tray.ui.SideDishMenuScreen
import com.astrick.compose.navigation.samples.lunch_tray.ui.StartOrderScreen
import com.astrick.compose.navigation.samples.lunch_tray.ui.navigation.LunchTrayNavDestination

@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier
) {
    
    val viewModel: OrderViewModel = viewModel()
    
    val navController = rememberNavController()
    val backStack by navController.currentBackStackEntryAsState()
    val current = LunchTrayNavDestination.valueOf(backStack?.destination?.route ?: LunchTrayNavDestination.START.name)
    
    Scaffold(
        topBar = {
            LunchTrayTopAppBar(
                title = stringResource(id = current.titleId),
                navigateUp = navController.previousBackStackEntry != null,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        
        NavHost(
            navController = navController,
            startDestination = LunchTrayNavDestination.START.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = LunchTrayNavDestination.START.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayNavDestination.ENTREE_MENU.name)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                        .fillMaxSize()
                )
            }
            composable(route = LunchTrayNavDestination.ENTREE_MENU.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        navController.popToStart()
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayNavDestination.SIDE_DISH_MENU.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateEntree(it)
                    },
                )
            }
            composable(route = LunchTrayNavDestination.SIDE_DISH_MENU.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        navController.popToStart()
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayNavDestination.ACCOMPANIMENT_MENU.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateSideDish(it)
                    },
                )
            }
            composable(route = LunchTrayNavDestination.ACCOMPANIMENT_MENU.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navController.popToStart()
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayNavDestination.CHECKOUT.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateAccompaniment(it)
                    },
                )
            }
            composable(route = LunchTrayNavDestination.CHECKOUT.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        navController.popToStart()
                    },
                    onCancelButtonClicked = {
                        navController.navigate(LunchTrayNavDestination.START.name)
                    },
                )
            }
        }
    }
}

private fun NavHostController.popToStart() {
    popBackStack(
        LunchTrayNavDestination.START.name,
        false
    )
}
