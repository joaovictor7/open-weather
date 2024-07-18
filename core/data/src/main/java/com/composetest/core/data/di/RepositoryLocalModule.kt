package com.composetest.core.data.di

import com.composetest.core.data.data.repositories.local.AppThemeRepository
import com.composetest.core.data.data.repositories.local.AppThemeRepositoryImpl
import com.composetest.core.data.data.repositories.local.DatabaseRepository
import com.composetest.core.data.data.repositories.local.DatabaseRepositoryImpl
import com.composetest.core.data.data.repositories.local.WorkManagerRepository
import com.composetest.core.data.data.repositories.local.WorkManagerRepositoryImpl
import com.composetest.core.data.data.repositories.remote.AnalyticsRepository
import com.composetest.core.data.data.repositories.remote.AnalyticsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryBindsModule {
    @Binds
    abstract fun appThemeRepository(appThemeRepositoryImpl: AppThemeRepositoryImpl): AppThemeRepository

    @Binds
    abstract fun workManagerRepository(workManagerRepositoryImpl: WorkManagerRepositoryImpl): WorkManagerRepository

    @Binds
    abstract fun analyticsRepository(analyticsRepositoryImpl: AnalyticsRepositoryImpl): AnalyticsRepository

    @Binds
    abstract fun databaseRepository(databaseRepositoryImpl: DatabaseRepositoryImpl): DatabaseRepository
}