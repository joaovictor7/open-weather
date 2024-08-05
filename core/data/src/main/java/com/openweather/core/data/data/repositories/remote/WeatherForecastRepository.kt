package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse

interface WeatherForecastRepository {
    suspend fun <T> getWeatherNow(mapper: (WeatherNowResponse) -> T): T

    suspend fun <T> getWeatherForecasts(mapper: (WeatherForecastResponse) -> T): T
}