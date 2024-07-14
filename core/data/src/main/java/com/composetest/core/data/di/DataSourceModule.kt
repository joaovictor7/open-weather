package com.composetest.core.data.di

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.datasources.local.PreferenceDataSourceImpl
import com.composetest.core.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun authenticationDataSource(authenticationDataSourceImpl: AuthenticationDataSourceImpl): AuthenticationDataSource

    @Binds
    abstract fun firebaseAnalyticsDataSource(firebaseAnalyticsDataSourceImpl: FirebaseAnalyticsDataSourceImpl): FirebaseAnalyticsDataSource

    @Binds
    abstract fun preferenceDataSource(preferenceDataSourceImpl: PreferenceDataSourceImpl): PreferenceDataSource
}
