package ca.qc.cstj.consortium.ui.screens.Home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cstj.consortium.data.database.AppDatabase
import ca.qc.cstj.consortium.data.repositories.TraderRepository
import ca.qc.cstj.consortium.models.Trader
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeScreenViewModel (application: Application) : AndroidViewModel(application){


    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val deliveryRepository = AppDatabase.getInstance(application).deliveryRepository()
    private val traderRepository = TraderRepository(application)

    private val _eventsFlow = MutableSharedFlow<ScreenEvent>()
    val eventsFlow = _eventsFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            traderRepository.settings.collect { settings ->

                val element1 = settings.iaspyx
                val element2 = settings.smiathil
                val element3 = settings.jasmalt
                val element4 = settings.vethyx
                val element5 = settings.blierium
                _uiState.update {
                    _uiState.value.copy(trader = Trader(
                        iaspyx=element1,
                        smiathil = element2,
                        jasmalt =element3,
                        vethyx = element4,
                        blierium = element5



                    ),

                    )
                }
            }
        }
    }

    private fun verifyUserEnter(): Boolean {
        return _uiState.value.trader.name.isBlank()
    }

    fun load() {
        viewModelScope.launch {

            try {
                if (uiState.value.isError){
                    throw Exception("no name")
                }


                traderRepository.saveCargo(
                    _uiState.value.trader.iaspyx+(Random.nextDouble(50.0,200.0).toFloat()),
                    _uiState.value.trader.smiathil+(Random.nextDouble(50.0,200.0).toFloat()),
                    _uiState.value.trader.jasmalt+(Random.nextDouble(50.0,200.0).toFloat()),
                    _uiState.value.trader.vethyx+(Random.nextDouble(50.0,200.0).toFloat()),
                    _uiState.value.trader.blierium+(Random.nextDouble(50.0,200.0).toFloat())
                )

                updateName()
                _eventsFlow.emit(ScreenEvent.Connected)
            }catch(ex: Exception) {
                _eventsFlow.emit(ScreenEvent.CanNotConnect(ex))
            }
        }

    }

    private fun updateName() {
        viewModelScope.launch {
            traderRepository.save(
                key = TraderRepository.PreferencesKeys.NAME,
                _uiState.value.traderNameField
            )
        }
    }

    fun upload() {
        viewModelScope.launch {

            try {
                if (uiState.value.isError){
                    throw Exception("no name")
                }


                deliveryRepository.deleteAll()

                updateName()
                _eventsFlow.emit(ScreenEvent.Connected)
            }catch(ex: Exception) {
                _eventsFlow.emit(ScreenEvent.CanNotConnect(ex))
            }
        }

    }


    fun open(): Boolean {
        if (!uiState.value.isError){
            updateName()
        }
    return !uiState.value.isError
    }

    fun updateNameField(newName:String) {
        updateName()
        _uiState.update {
            _uiState.value.copy(
                traderNameField = newName,
                isError = verifyUserEnter()
            )

        }


    }

        sealed class ScreenEvent {

            data object Connected : ScreenEvent()
            data class CanNotConnect(val ex: Exception) : ScreenEvent()

        }

    }