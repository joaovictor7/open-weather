package com.composetest.core.data.di

import com.composetest.core.data.providers.WorkManagerProvider
import com.composetest.core.data.providers.WorkManagerProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {
    @Binds
    abstract fun workManagerProvider(workManagerProviderImpl: WorkManagerProviderImpl): WorkManagerProvider
}