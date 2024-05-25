package com.composetest.core.database.appdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.domain.constants.DatabaseConfig
import com.composetest.core.database.entities.UserEntity

@Database(
    version = DatabaseConfig.DATABASE_VERSION,
    entities = [
        UserEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserEntityDao
}