package com.openweather.core.domain.mappers

import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse
import com.openweather.core.domain.models.TodayWeatherForecastModel
import javax.inject.Inject

class TodayWeatherForecastMapper @Inject constructor() {

    operator fun invoke(response: TodayWeatherForecastResponse): TodayWeatherForecastModel {
        val lastWeather = response.weather.last()
        return TodayWeatherForecastModel(
            cityName = response.cityName,
            temperature = "${response.temperatureData.temperature}º",
            iconId = lastWeather.icon,
            weatherDescription = lastWeather.description
        )
    }
}