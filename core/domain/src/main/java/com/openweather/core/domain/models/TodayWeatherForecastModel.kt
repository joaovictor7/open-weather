package com.openweather.core.domain.models

data class TodayWeatherForecastModel(
    val minTemperature: Float,
    val maxTemperature: Float,
    val temperatures: List<Float>
)
