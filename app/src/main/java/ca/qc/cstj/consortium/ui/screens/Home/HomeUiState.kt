package ca.qc.cstj.consortium.ui.screens.Home

import ca.qc.cstj.consortium.models.Delivery
import ca.qc.cstj.consortium.models.Trader

data class HomeUiState (
    val deliveries: List<Delivery> = listOf(),
    val trader: Trader = Trader(),
    val isError: Boolean = true,
)