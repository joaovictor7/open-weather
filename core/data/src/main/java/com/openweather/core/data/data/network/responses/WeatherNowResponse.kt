package com.openweather.core.data.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherNowResponse(
    @SerialName("name") val city: String,
    @SerialName("main") val temperatureData: WeatherNowTemperatureResponse,
    @SerialName("weather") val weatherDataList: List<WeatherNowDataResponse>
)

@Serializable
data class WeatherNowTemperatureResponse(
    @SerialName("temp") val temperature: Float,
)

@Serializable
data class WeatherNowDataResponse(
    val icon: String,
    val description: String
)