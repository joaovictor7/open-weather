package com.openweather.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.openweather.core.database.converters.LocalDateTimeConverter
import com.openweather.core.database.constants.DatabaseConfig
import com.openweather.core.database.entities.SessionEntity
import com.openweather.core.database.entities.UserEntity

@Database(
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        SessionEntity::class,
        UserEntity::class
    ]
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

}