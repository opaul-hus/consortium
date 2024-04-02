package ca.qc.cstj.consortium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ca.qc.cstj.consortium.ui.screens.Deliveries.DeliveriesScreen
import ca.qc.cstj.consortium.ui.screens.Home.HomeScreen
import ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen.NewDeliveryScreen


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
        HomeScreen(navController = navController)
        }
        composable(Screen.NewDelivery.route) {
        NewDeliveryScreen(navController = navController)
        }
        composable(Screen.Deliveries.route) {
        DeliveriesScreen(navController = navController)
        }
    }
}