package ca.qc.cstj.consortium.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Entity (tableName = "deliveries")
data class Delivery(

    @PrimaryKey (autoGenerate = true)
    val idDelivery: Int = 0,

    @ColumnInfo(name="iaspyx")
    val iaspyx: Float = 0f,

    @ColumnInfo(name="smiathil")
    val smiathil: Float = 0f,

    @ColumnInfo(name="jasmalt")
    val jasmalt: Float = 0f,

    @ColumnInfo(name="vethyx")
    val vethyx: Float = 0f,

    @ColumnInfo(name="blierium")
    val blierium: Float = 0f,

    @ColumnInfo(name = "deliveryDate")
    val deliveryDate: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
)
