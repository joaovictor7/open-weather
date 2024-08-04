package com.openweather.feature.home.models

data class FutureDayForecast(
    val day: String,
    val forecast: List<FutureDayHourlyForecast>
)
