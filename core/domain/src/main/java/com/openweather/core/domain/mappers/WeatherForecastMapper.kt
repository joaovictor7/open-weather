package com.openweather.core.domain.mappers

import com.openweather.common.extensions.unixToLocalDateTime
import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.domain.models.FutureDailyWeatherForecastModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.domain.models.WeatherForecastsModel
import java.time.LocalDate
import javax.inject.Inject

class WeatherForecastMapper @Inject constructor() {

    operator fun invoke(
        todayWeatherForecasts: List<WeatherForecastDataResponse>,
        futureWeatherForecasts: Map<LocalDate, List<WeatherForecastDataResponse>>,
    ) = WeatherForecastsModel(
        todayWeatherForecast = todayWeatherForecasts.mapperToTodayWeatherForecastModel(),
        futureWeatherForecasts = futureWeatherForecasts.mapperToFutureWeatherForecastModels()
    )

    private fun List<WeatherForecastDataResponse>.mapperToTodayWeatherForecastModel() =
        TodayWeatherForecastModel(
            minTemperature = minOf { it.temperatureData.minTemperature },
            maxTemperature = maxOf { it.temperatureData.maxTemperature },
            temperatures = map { it.temperatureData.temperature }
        )

    private fun Map<LocalDate, List<WeatherForecastDataResponse>>.mapperToFutureWeatherForecastModels() =
        map { futureWeatherForecast ->
            FutureWeatherForecastModel(
                date = futureWeatherForecast.key,
                futureDailyWeatherForecasts = futureWeatherForecast.value.map {
                    FutureDailyWeatherForecastModel(
                        dateTime = it.dateTime.unixToLocalDateTime,
                        temperature = it.temperatureData.temperature,
                        iconId = String()
                    )
                }
            )
        }
}