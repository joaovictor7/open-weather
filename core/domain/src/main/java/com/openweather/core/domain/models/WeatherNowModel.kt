package com.openweather.core.domain.models

data class WeatherNowModel(
    val city: String,
    val temperature: Float,
    val iconId: String,
    val description: String
)
