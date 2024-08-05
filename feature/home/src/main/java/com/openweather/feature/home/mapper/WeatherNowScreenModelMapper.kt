package com.openweather.feature.home.mapper

import com.openweather.common.providers.LocaleProvider
import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.feature.home.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor(
    private val localeProvider: LocaleProvider
) {

    operator fun invoke(weatherNowModel: WeatherNowModel) = WeatherNowScreenModel(
        city = weatherNowModel.city,
        temperature = "${weatherNowModel.temperature.toInt()}º",
        iconId = weatherNowModel.iconId,
        description = weatherNowModel.description.replaceFirstChar {
            it.titlecase(localeProvider.default)
        }
    )
}