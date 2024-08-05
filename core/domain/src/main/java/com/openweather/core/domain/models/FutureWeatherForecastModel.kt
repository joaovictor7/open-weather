package com.openweather.core.domain.models

import java.time.LocalDate

data class FutureWeatherForecastModel(
    val date: LocalDate,
    val futureDailyWeatherForecasts: List<FutureDailyWeatherForecastModel>
)