package com.openweather.core.domain.models

data class FutureWeatherForecastModel(
    val day: String,
    val weatherForecasts: List<FutureWeatherForecastDailyModel>
)