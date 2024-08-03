package com.openweather.core.data.models.responses.weatherforecast

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FutureWeatherForecastResponse(
    @SerialName("list") val forecastList: List<FutureWeatherForecastDataResponse>
)

@Serializable
data class FutureWeatherForecastDataResponse(
    @SerialName("dt") val dateTime: Float,
    @SerialName("main") val temperatureData: ForecastTemperatureDataResponse
)