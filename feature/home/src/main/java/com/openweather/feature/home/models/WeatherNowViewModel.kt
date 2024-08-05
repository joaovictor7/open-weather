package com.openweather.feature.home.models

data class WeatherNowViewModel(
    val city: String = String(),
    val temperature: String = String(),
    val iconId: String = String(),
    val description: String = String()
)
