package com.openweather.core.domain.models

data class WeatherNowModel(
    val city: String,
    val temperature: String,
    val iconId: String,
    val description: String
)
