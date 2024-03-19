package ca.qc.cstj.consortium.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {

        }
        composable(Screen.NewDelivery.route) {

        }
        composable(Screen.Deliveries.route) {

        }
    }
}