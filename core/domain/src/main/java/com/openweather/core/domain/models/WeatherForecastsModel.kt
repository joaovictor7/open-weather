package com.openweather.core.domain.models

data class WeatherForecastsModel(
    val todayWeatherForecast: TodayWeatherForecastModel,
    val futureWeatherForecasts: List<FutureWeatherForecastModel>
)