package ca.qc.cstj.consortium.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import ca.qc.cstj.consortium.models.Trader
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "consortium-datastore")
class TraderRepository(private val context: Context) {

    object PreferencesKeys {
        //Dans la TP
        //val TP_ELEMENT = floatPreferencesKey("element1")
        val ELEMENT_I = floatPreferencesKey("element1")
        val ELEMENT_SM = floatPreferencesKey("element2")
        val ELEMENT_JA = floatPreferencesKey("element3")
        val ELEMENT_VE = floatPreferencesKey("element4")
        val ELEMENT_B = floatPreferencesKey("element5")
        val NAME = stringPreferencesKey("name")
    }

    val settings = context.dataStore.data.map {
        val traderName = it[PreferencesKeys.NAME] ?: "Olivier"
        val element1 = it[PreferencesKeys.ELEMENT_I] ?: 200f
        val element2 = it[PreferencesKeys.ELEMENT_SM] ?: 200f
        val element3 = it[PreferencesKeys.ELEMENT_JA] ?: 200f
        val element4 = it[PreferencesKeys.ELEMENT_VE] ?: 200f
        val element5 = it[PreferencesKeys.ELEMENT_B] ?: 200f

        Trader(
            name = traderName,
            iaspyx = element1,
            smiathil = element2,
            jasmalt = element3,
            vethyx = element4,
            blierium = element5
        )


    }

    suspend fun saveCargo(quantity1:Float,quantity2:Float,quantity3:Float,quantity4:Float,quantity5:Float){
        context.dataStore.edit {
            it[PreferencesKeys.ELEMENT_I]=quantity1
            it[PreferencesKeys.ELEMENT_SM]=quantity2
            it[PreferencesKeys.ELEMENT_JA]=quantity3
            it[PreferencesKeys.ELEMENT_VE]=quantity4
            it[PreferencesKeys.ELEMENT_B]=quantity5
        }
    }

    suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit {
            it[key] = value
        }



    }
}