package com.openweather.core.domain.models

data class TodayWeatherForecastModel(
    val cityName: String,
    val temperature: String,
    val iconId: String,
    val weatherDescription: String
)
