package com.openweather.core.data.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    @SerialName("list") val dataList: List<WeatherForecastDataResponse>
)

@Serializable
data class WeatherForecastDataResponse(
    @SerialName("dt") val dateTime: Long,
    @SerialName("main") val temperatureData: WeatherForecastTemperatureResponse
)

@Serializable
data class WeatherForecastTemperatureResponse(
    @SerialName("temp") val temperature: Float,
    @SerialName("temp_min") val minTemperature: Float,
    @SerialName("temp_max") val maxTemperature: Float
)