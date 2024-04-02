package ca.qc.cstj.consortium.ui.screens.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.consortium.data.database.AppDatabase
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.models.Trader
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel (application: Application) : AndroidViewModel(application){
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val deliveryRepository = AppDatabase.getInstance(application).deliveryRepository()
    private val traderRepository = TraderRepository(application)


        init {
            viewModelScope.launch {

                deliveryRepository.retrieveAll().collect() { deliveries ->
                    _uiState.update {
                        _uiState.value.copy(deliveries = deliveries)
                    }

                }

                traderRepository.settings.collect { settings ->
                    val name = settings.name
                    val element1 = settings.iaspyx
                    val element2 = settings.smiathil
                    val element3 = settings.jasmalt
                    val element4 = settings.vethyx
                    val element5 = settings.blierium
                    _uiState.update {
                        _uiState.value.copy(
                            trader = Trader(
                                name = name,
                                iaspyx = element1,
                                smiathil = element2,
                                jasmalt = element3,
                                vethyx = element4,
                                blierium = element5
                            )
                        )


                    }
                }

            }
        }

}