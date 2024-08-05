package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse

internal interface OpenWeatherDataSource {
    suspend fun getWeatherNow(request: WeatherForecastRequest): WeatherNowResponse
    suspend fun getFutureWeatherForecast(request: WeatherForecastRequest): WeatherForecastResponse
}