package com.openweather.core.domain.usecases.weatherforecast

import com.openweather.common.extensions.unixToLocalDateTime
import com.openweather.common.providers.DateTimeProvider
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mappers.FutureWeatherForecastMapper
import com.openweather.core.domain.models.WeatherForecastsModel
import javax.inject.Inject

class GetWeatherForecastsUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val futureWeatherForecastMapper: FutureWeatherForecastMapper,
    private val dateTimeProvider: DateTimeProvider
) {

    suspend operator fun invoke(): WeatherForecastsModel =
        weatherForecastRepository.getFutureWeatherForecast { response ->
            val todayWeatherForecasts = response.dataList.filter {
                it.dateTime.unixToLocalDateTime.toLocalDate() <= dateTimeProvider.nowDateTime.toLocalDate()
            }
            val futureWeatherForecasts = response.dataList
                .filterNot {
                    it.dateTime.unixToLocalDateTime.toLocalDate() == dateTimeProvider.nowDateTime.toLocalDate()
                }.groupBy {
                    it.dateTime.unixToLocalDateTime.toLocalDate()
                }
            futureWeatherForecastMapper(todayWeatherForecasts, futureWeatherForecasts)
        }
}