package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.data.network.responses.FutureWeatherForecastResponse
import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse

interface WeatherForecastRepository {
    suspend fun <T> getTodayWeatherForecast(mapper: (TodayWeatherForecastResponse) -> T): T

    suspend fun <T> getFutureWeatherForecast(mapper: (FutureWeatherForecastResponse) -> T): T
}