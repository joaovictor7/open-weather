package com.openweather.feature.home.models

internal data class WeatherNowScreenModel(
    val city: String = String(),
    val temperature: String = String(),
    val iconId: String = String(),
    val description: String = String()
)
