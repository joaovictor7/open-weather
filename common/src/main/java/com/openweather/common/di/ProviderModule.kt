package com.openweather.common.di

import com.openweather.common.providers.DateTimeProvider
import com.openweather.common.providers.DateTimeProviderImpl
import com.openweather.common.providers.LocaleProvider
import com.openweather.common.providers.LocaleProviderImpl
import com.openweather.common.providers.NetworkProvider
import com.openweather.common.providers.NetworkProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {
    @Binds
    abstract fun dateTimeProvider(dateTimeProviderImpl: DateTimeProviderImpl): DateTimeProvider

    @Binds
    abstract fun networkProvider(networkProviderImpl: NetworkProviderImpl): NetworkProvider

    @Binds
    abstract fun localeProvider(localeProviderImpl: LocaleProviderImpl): LocaleProvider
}