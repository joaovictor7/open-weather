package com.openweather.feature.home.mappers

import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.feature.home.models.WeatherNowScreenModel
import javax.inject.Inject

internal class WeatherNowScreenModelMapper @Inject constructor() {

    operator fun invoke(weatherNowModel: WeatherNowModel) = WeatherNowScreenModel(
        city = weatherNowModel.city,
        temperature = "${weatherNowModel.temperature.toInt()}º",
        iconId = weatherNowModel.iconId,
        description = weatherNowModel.description
    )
}