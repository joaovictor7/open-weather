package com.openweather.core.data.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastTemperatureDataResponse(
    @SerialName("temp") val temperature: Float,
    @SerialName("temp_min") val minTemperature: Float? = null,
    @SerialName("temp_max") val maxTemperature: Float? = null,
)