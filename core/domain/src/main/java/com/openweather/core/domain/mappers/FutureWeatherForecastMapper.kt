package com.openweather.core.domain.mappers

import com.openweather.common.extensions.unixToLocalDateTime
import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.domain.models.FutureWeatherForecastDailyModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.domain.models.WeatherForecastsModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class FutureWeatherForecastMapper @Inject constructor() {

    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val dateToStringFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")

    operator fun invoke(
        todayWeatherForecasts: List<WeatherForecastDataResponse>,
        futureWeatherForecasts: Map<LocalDate, List<WeatherForecastDataResponse>>,
    ) = WeatherForecastsModel(
        todayWeatherForecast = todayWeatherForecasts.convertToTodayWeatherForecastModel(),
        futureWeatherForecasts =  futureWeatherForecasts.mapperToFutureWeatherForecastsModel()
    )

    private fun List<WeatherForecastDataResponse>.convertToTodayWeatherForecastModel() =
        TodayWeatherForecastModel(
            minTemperature = minOf { it.temperatureData.minTemperature },
            maxTemperature = maxOf { it.temperatureData.maxTemperature },
            temperatures = map { it.temperatureData.temperature }
        )

    private fun Map<LocalDate, List<WeatherForecastDataResponse>>.mapperToFutureWeatherForecastsModel() =
        map { futureWeatherForecast ->
            FutureWeatherForecastModel(
                day = futureWeatherForecast.key.format(dateToStringFormatter),
                weatherForecasts = futureWeatherForecast.value.map {
                    FutureWeatherForecastDailyModel(
                        hour = it.dateTime.unixToLocalDateTime
                            .format(dateToHourFormatter)
                            .toString(),
                        temperature = "${it.temperatureData.temperature.toInt()}º",
                        iconId = String()
                    )
                }
            )
        }
}