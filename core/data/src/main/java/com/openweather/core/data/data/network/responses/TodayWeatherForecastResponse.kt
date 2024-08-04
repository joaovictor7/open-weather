package com.openweather.core.data.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TodayWeatherForecastResponse(
    @SerialName("name") val cityName: String,
    @SerialName("main") val temperatureData: ForecastTemperatureDataResponse,
    val weather: List<TodayWeatherResponse>
)

@Serializable
data class TodayWeatherResponse(
    val icon: String,
    val description: String
)