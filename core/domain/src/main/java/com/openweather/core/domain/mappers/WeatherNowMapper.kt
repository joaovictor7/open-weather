package com.openweather.core.domain.mappers

import com.openweather.core.data.data.network.responses.WeatherNowDataResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.domain.models.WeatherNowModel
import javax.inject.Inject

class WeatherNowMapper @Inject constructor() {

    operator fun invoke(
        response: WeatherNowResponse,
        weatherNowDataResponse: WeatherNowDataResponse
    ) = WeatherNowModel(
        city = response.city,
        temperature = response.temperatureData.temperature,
        iconId = weatherNowDataResponse.icon,
        description = weatherNowDataResponse.description
    )
}