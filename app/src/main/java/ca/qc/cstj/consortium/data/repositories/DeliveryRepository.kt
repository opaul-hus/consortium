package ca.qc.cstj.consortium.data.repositories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ca.qc.cstj.consortium.models.Delivery
import kotlinx.coroutines.flow.Flow

@Dao
interface DeliveryRepository {

    @Insert
    suspend fun create(del: Delivery)

    @Query("SELECT * FROM deliveries")
    suspend fun retrieveAllWithoutFlow() : List<Delivery>

    @Query("SELECT * FROM deliveries")
    fun retrieveAll() : Flow<List<Delivery>>

    @Delete
    suspend fun delete(note: Delivery)

    @Query("DELETE FROM deliveries")
    suspend fun deleteAll()
}