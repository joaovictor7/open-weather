package com.openweather.core.data.di

import com.openweather.core.data.data.repositories.remote.AnalyticsRepository
import com.openweather.core.data.data.repositories.remote.AnalyticsRepositoryImpl
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    // Locals
    @Binds
    abstract fun analyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl
    ): AnalyticsRepository


    // Remotes
    @Binds
    abstract fun weatherForecastRepository(
        weatherForecastRepositoryImpl: WeatherForecastRepositoryImpl
    ): WeatherForecastRepository
}