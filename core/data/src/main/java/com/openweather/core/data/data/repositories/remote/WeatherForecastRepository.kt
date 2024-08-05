package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse

interface WeatherForecastRepository {
    suspend fun getWeatherNow(): WeatherNowResponse

    suspend fun getWeatherForecasts(): WeatherForecastResponse
}