package com.openweather.feature.home.mapper

import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.feature.home.models.FutureDailyWeatherForecastScreenModel
import com.openweather.feature.home.models.FutureWeatherForecastScreenModel
import java.time.format.DateTimeFormatter
import javax.inject.Inject

internal class FutureWeatherForecastScreenModelsMapper @Inject constructor() {

    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val dateToStringFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")

    operator fun invoke(futureWeatherForecastModel: List<FutureWeatherForecastModel>) =
        futureWeatherForecastModel.map { futureWeatherForecast ->
            FutureWeatherForecastScreenModel(
                day = futureWeatherForecast.date.format(dateToStringFormatter),
                futureDailyWeatherForecasts = futureWeatherForecast.futureDailyWeatherForecasts.map {
                    FutureDailyWeatherForecastScreenModel(
                        hour = "${it.dateTime.format(dateToHourFormatter)}h",
                        temperature = "${it.temperature.toInt()}º",
                        iconId = String()
                    )
                }
            )
        }
}