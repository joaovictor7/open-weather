package com.composetest.di

import com.composetest.common.utility.providers.BuildConfigProvider
import com.composetest.providers.BuildConfigProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {
    @Binds
    @Singleton
    abstract fun buildConfigProvider(buildConfigProviderImpl: BuildConfigProviderImpl): BuildConfigProvider
}