package com.composetest.core.database.di

import android.content.Context
import androidx.room.Room
import com.composetest.core.database.appdatabase.AppDatabase
import com.composetest.core.database.domain.constants.DatabaseConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {
    @Provides
    fun database(
        @ApplicationContext context: Context
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DatabaseConfig.DATABASE_NAME
    ).build()
}