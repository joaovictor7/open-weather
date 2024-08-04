package com.openweather.core.domain.mappers

import com.openweather.common.extensions.unixToLocalDateTime
import com.openweather.core.data.data.network.responses.FutureWeatherForecastResponse
import com.openweather.core.domain.models.FutureWeatherForecastModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FutureWeatherForecastMapper @Inject constructor() {

    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")

    operator fun invoke(response: FutureWeatherForecastResponse) = response.forecastList.map {
        FutureWeatherForecastModel(
            hour = it.dateTime.unixToLocalDateTime.format(dateToHourFormatter),
            temperature = "${it.temperatureData.temperature}º"
        )
    }
}