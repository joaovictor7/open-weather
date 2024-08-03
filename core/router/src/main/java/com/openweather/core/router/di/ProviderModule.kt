package com.openweather.core.router.di

import com.openweather.core.router.providers.NavHostControllerProvider
import com.openweather.core.router.providers.NavHostControllerProviderImpl
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
    abstract fun navHostControllerProvider(
        navControllerProviderImpl: NavHostControllerProviderImpl
    ): NavHostControllerProvider
}