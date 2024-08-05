package com.openweather.feature.home.models

internal data class FutureWeatherForecastScreenModel(
    val day: String,
    val futureDailyWeatherForecasts: List<FutureDailyWeatherForecastScreenModel>
)