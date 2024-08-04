package com.openweather.core.data.data.network.requests

data class WeatherForecastRequest(
    val latitude: String,
    val longitude: String,
    val appId: String,
    val language: String,
    val metric: String
)
