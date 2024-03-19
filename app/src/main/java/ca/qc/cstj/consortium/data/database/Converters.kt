package ca.qc.cstj.consortium.data.database

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

class Converters {

    @TypeConverter
    fun toLocalDateTime(value: String): LocalDateTime {
        return value.let {
            LocalDateTime.parse(value)
        }
    }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime): String {
        return dateTime.toString()
    }

}