package ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen

import ca.qc.cstj.consortium.models.Trader

data class NewDeliveryUiState (
    val quantityI:Float= Trader().iaspyx,
    val quantitySm:Float = Trader().smiathil,
    val quantityJa:Float = Trader().jasmalt,
    val  quantityVe:Float = Trader().vethyx,
    val quantityB:Float = Trader().blierium,



)