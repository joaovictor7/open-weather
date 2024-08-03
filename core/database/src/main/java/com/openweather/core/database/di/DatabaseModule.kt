package com.openweather.core.database.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.openweather.common.providers.BuildConfigProvider
import com.openweather.core.database.constants.DatabaseConfig
import com.openweather.core.database.converters.LocalDateTimeConverter
import com.openweather.core.database.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context,
        buildConfigProvider: BuildConfigProvider,
    ): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DatabaseConfig.DATABASE_NAME
    )
        .addTypeConverter(LocalDateTimeConverter())
        .apply {
            if (!buildConfigProvider.get.isRelease) {
                setQueryCallback({ sqlQuery, bindArgs ->
                    Log.i("SQLite", "SQL Query: $sqlQuery")
                    if (bindArgs.isNotEmpty()) Log.i("SQLite", "SQL Args: $bindArgs")
                }, Executors.newSingleThreadExecutor())
            }
        }
        .build()
}