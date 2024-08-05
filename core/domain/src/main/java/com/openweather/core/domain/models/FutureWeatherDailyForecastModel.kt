package com.openweather.core.domain.models

data class FutureWeatherForecastDailyModel(
    val iconId: String,
    val temperature: String,
    val hour: String
)