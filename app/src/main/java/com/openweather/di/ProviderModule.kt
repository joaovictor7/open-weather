package com.openweather.di

import com.openweather.common.providers.BuildConfigProvider
import com.openweather.providers.BuildConfigProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {
    @Binds
    abstract fun buildConfigProvider(buildConfigProviderImpl: BuildConfigProviderImpl): BuildConfigProvider
}