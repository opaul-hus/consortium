package ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.consortium.data.database.AppDatabase
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.models.Delivery
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewDeliveryScreenViewModel(application: Application) : AndroidViewModel(application)  {

    private val _uiState = MutableStateFlow(NewDeliveryUiState())
    val uiState = _uiState.asStateFlow()


    private val _eventsFlow = MutableSharedFlow<ScreenEvent>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    private val deliveryRepository = AppDatabase.getInstance(application).deliveryRepository()

    private val traderRepository = TraderRepository(application)

    init {
        viewModelScope.launch {
            traderRepository.settings.collect { settings ->
                val element1 = settings.iaspyx
                val element2 = settings.smiathil
                val element3 = settings.jasmalt
                val element4 = settings.vethyx
                val element5= settings.blierium
                _uiState.update {
                    _uiState.value.copy(quantityI = element1, quantitySm = element2, quantityJa = element3, quantityVe = element4, quantityB = element5)
                }
            }
        }
    }
    fun save() {

        //Lancement d'un thread/coroutine
        viewModelScope.launch {
            try {

                deliveryRepository.create(del = Delivery(
                    iaspyx = _uiState.value.quantityI,
                    smiathil = _uiState.value.quantitySm,
                    jasmalt = _uiState.value.quantityJa,
                    vethyx = _uiState.value.quantityVe,
                    blierium = _uiState.value.quantityB
                    ))
                _eventsFlow.emit(ScreenEvent.DeliverySaved)
            } catch(ex: Exception) {
                _eventsFlow.emit(ScreenEvent.DeliveryCannotBeSaved(ex))
            }
        }

    }



    sealed class ScreenEvent {

        data object DeliverySaved : ScreenEvent()
        data class DeliveryCannotBeSaved(val ex: Exception) : ScreenEvent()

    }
}