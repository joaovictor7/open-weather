package com.openweather.core.domain.mappers

import com.openweather.common.extensions.orZero
import com.openweather.common.providers.LocaleProvider
import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse
import com.openweather.core.domain.models.TodayWeatherForecastModel
import java.util.Locale
import javax.inject.Inject

class TodayWeatherForecastMapper @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    operator fun invoke(response: TodayWeatherForecastResponse): TodayWeatherForecastModel {
        val lastWeather = response.weather.last()
        return TodayWeatherForecastModel(
            cityName = response.cityName,
            temperature = "${String.format(Locale.US, "%.1f", response.temperatureData.temperature)}º",
            minTemperature = response.temperatureData.minTemperature.orZero,
            maxTemperature = response.temperatureData.maxTemperature.orZero,
            iconId = lastWeather.icon,
            weatherDescription = lastWeather.description.replaceFirstChar {
                it.titlecase(localeProvider.default)
            }
        )
    }
}