package com.openweather.core.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDateTime

@ProvidedTypeConverter
internal class LocalDateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDateTime? =
        value?.let { LocalDateTime.parse(it) }

    @TypeConverter
    fun toTimestamp(date: LocalDateTime?): String? {
        return date?.toString()
    }
}