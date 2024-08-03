package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastResponse
import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherForecastResponse

interface WeatherForecastRepository {
    suspend fun <T> getTodayWeatherForecast(mapper: (TodayWeatherForecastResponse) -> T): T

    suspend fun <T> getFutureWeatherForecast(mapper: (FutureWeatherForecastResponse) -> T): T
}