package com.openweather.core.domain.models

import java.time.LocalDateTime

data class FutureDailyWeatherForecastModel(
    val iconId: String,
    val temperature: Float,
    val dateTime: LocalDateTime
)