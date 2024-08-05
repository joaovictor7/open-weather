package com.openweather.core.domain.mappers

import com.openweather.common.providers.LocaleProvider
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.domain.models.WeatherNowModel
import javax.inject.Inject

class WeatherNowMapper @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    operator fun invoke(response: WeatherNowResponse): WeatherNowModel {
        val lastWeather = response.weatherDataList.last()
        return WeatherNowModel(
            city = response.city,
            temperature = response.temperatureData.temperature,
            iconId = lastWeather.icon,
            description = lastWeather.description.replaceFirstChar {
                it.titlecase(localeProvider.default)
            }
        )
    }
}