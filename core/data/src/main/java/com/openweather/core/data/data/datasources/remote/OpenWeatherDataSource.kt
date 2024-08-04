package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.data.network.responses.FutureWeatherForecastResponse
import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse

internal interface OpenWeatherDataSource {
    suspend fun getCurrentWeatherForecast(request: WeatherForecastRequest): TodayWeatherForecastResponse
    suspend fun getFutureWeatherForecast(request: WeatherForecastRequest): FutureWeatherForecastResponse
}