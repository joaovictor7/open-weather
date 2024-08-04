package com.openweather.core.domain.usecases.weatherforecast

import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mappers.TodayWeatherForecastMapper
import com.openweather.core.domain.models.TodayWeatherForecastModel
import javax.inject.Inject

class GetTodayWeatherForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val todayWeatherForecastMapper: TodayWeatherForecastMapper
) {

    suspend operator fun invoke(): TodayWeatherForecastModel =
        weatherForecastRepository.getTodayWeatherForecast(todayWeatherForecastMapper::invoke)
}