package com.openweather.core.database.di

import com.openweather.core.database.daos.SessionEntityDao
import com.openweather.core.database.daos.UserEntityDao
import com.openweather.core.database.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun sessionEntityDao(appDatabase: AppDatabase): SessionEntityDao = appDatabase.sessionEntityDao()

    @Provides
    fun userEntityDao(appDatabase: AppDatabase): UserEntityDao = appDatabase.userEntityDao()
}