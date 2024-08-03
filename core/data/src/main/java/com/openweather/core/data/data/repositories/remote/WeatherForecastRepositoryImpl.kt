package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastResponse
import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherForecastResponse
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource
) : WeatherForecastRepository {

    override suspend fun <T> getTodayWeatherForecast(mapper: (TodayWeatherForecastResponse) -> T): T {
        val response = openWeatherDataSource.getCurrentWeatherForecast()
        return mapper(response)
    }

    override suspend fun <T> getFutureWeatherForecast(mapper: (FutureWeatherForecastResponse) -> T): T {
        val response = openWeatherDataSource.getFutureWeatherForecast()
        return mapper(response)
    }
}