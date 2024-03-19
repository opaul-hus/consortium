package ca.qc.cstj.consortium.ui.navigation

sealed class Screen(val route :String) {

    data object Home : Screen("home")
    data object NewDelivery : Screen("new")
    data object Deliveries : Screen("deliveries")

}
