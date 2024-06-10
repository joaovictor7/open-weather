package com.composetest.common.di

import com.composetest.common.providers.DataSourceProvider
import com.composetest.common.providers.DataSourceProviderImpl
import com.composetest.common.providers.RemoteCallProvider
import com.composetest.common.providers.RemoteCallProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {
    @Binds
    abstract fun dataSourceProvider(dataSourceProviderImpl: DataSourceProviderImpl): DataSourceProvider

    @Binds
    abstract fun remoteCallProvider(remoteCallProviderImpl: RemoteCallProviderImpl): RemoteCallProvider
}