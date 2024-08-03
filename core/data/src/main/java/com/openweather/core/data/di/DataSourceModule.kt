package com.openweather.core.data.di

import com.openweather.core.data.data.datasources.local.PreferenceDataSource
import com.openweather.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSource
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindsModule {

    @Binds
    abstract fun firebaseAnalyticsDataSource(
        firebaseAnalyticsDataSourceImpl: FirebaseAnalyticsDataSourceImpl
    ): FirebaseAnalyticsDataSource

    @Binds
    abstract fun preferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

}