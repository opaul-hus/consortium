package ca.qc.cstj.consortium.ui.screens.NewDeliveryScreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.consortium.core.Constants
import ca.qc.cstj.consortium.data.database.AppDatabase
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.models.Delivery
import ca.qc.cstj.consortium.models.Trader
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
                val name = settings.name
                val element1 = settings.iaspyx
                val element2 = settings.smiathil
                val element3 = settings.jasmalt
                val element4 = settings.vethyx
                val element5 = settings.blierium
                _uiState.update {
                    _uiState.value.copy(trader = Trader(name = name,
                        iaspyx=element1,
                        smiathil = element2,
                        jasmalt =element3,
                        vethyx = element4,
                        blierium = element5



                    ),
                   slider1 = element1,
                   slider2 = element2,
                   slider3 = element3,
                   slider4 = element4,
                   slider5 = element5
                    )
                }
            }
        }
    }

    fun update(element:String ,quantity:Float){

           when(element){
               Constants.ELEMENTS[0]->
                   _uiState.update {_uiState.value.copy(slider1 = quantity)}
               Constants.ELEMENTS[1]->
                   _uiState.update { _uiState.value.copy(slider2 = quantity)}
               Constants.ELEMENTS[2]->
                   _uiState.update { _uiState.value.copy(slider3 = quantity)}
               Constants.ELEMENTS[3]->
                   _uiState.update {_uiState.value.copy(slider4 = quantity)}
               Constants.ELEMENTS[4]->
                   _uiState.update {_uiState.value.copy(slider5 = quantity)}
               else->{
                   return
               }

           }
       }

    fun save() {

        //Lancement d'un thread/coroutine
        viewModelScope.launch {
            try {

                if (_uiState.value.slider1+_uiState.value.slider2+_uiState.value.slider3+_uiState.value.slider4+_uiState.value.slider5==0f)
                {
                    throw (Exception())
                }
                deliveryRepository.create(del = Delivery(
                    iaspyx = _uiState.value.slider1,
                    smiathil = _uiState.value.slider2,
                    jasmalt = _uiState.value.slider3,
                    vethyx = _uiState.value.slider4,
                    blierium = _uiState.value.slider5
                    ))

                traderRepository.saveCargo(
                    uiState.value.trader.iaspyx-uiState.value.slider1,
                    uiState.value.trader.smiathil-uiState.value.slider2,
                    uiState.value.trader.jasmalt-uiState.value.slider3,
                    uiState.value.trader.vethyx-uiState.value.slider4,
                    uiState.value.trader.blierium-uiState.value.slider5
                    )


                _eventsFlow.emit(ScreenEvent.DeliverySaved)
            } catch(ex: Exception) {
                _eventsFlow.emit(ScreenEvent.DeliveryCannotBeSaved(ex))
            }
            finally {

            }
        }

    }



    sealed class ScreenEvent {

        data object DeliverySaved : ScreenEvent()
        data class DeliveryCannotBeSaved(val ex: Exception) : ScreenEvent()

    }
}