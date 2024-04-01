package ca.qc.cstj.consortium.ui.screens.Deliveries

import ca.qc.cstj.consortium.models.Delivery
import ca.qc.cstj.consortium.models.Trader

data class DeliveriesUiState (
    val deliveries: List<Delivery> = listOf(),
    val trader: Trader=Trader()
)