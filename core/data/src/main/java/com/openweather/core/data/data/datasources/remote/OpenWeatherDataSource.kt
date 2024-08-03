package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherForecastResponse
import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastResponse

internal interface OpenWeatherDataSource {
    suspend fun getCurrentWeatherForecast(): TodayWeatherForecastResponse
    suspend fun getFutureWeatherForecast(): FutureWeatherForecastResponse
}