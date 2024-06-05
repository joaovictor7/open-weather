package com.composetest.core.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.composetest.core.database.converters.LocalDateTimeConverter
import com.composetest.core.database.daos.SessionEntityDao
import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.domain.constants.DatabaseConfig
import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.UserEntity

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
    abstract fun userDao(): UserEntityDao
    abstract fun sessionDao(): SessionEntityDao
}