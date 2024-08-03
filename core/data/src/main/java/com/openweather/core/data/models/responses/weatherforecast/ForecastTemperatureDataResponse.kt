package com.openweather.core.data.models.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastTemperatureDataResponse(
    @SerialName("temp") val temperature: Double
)