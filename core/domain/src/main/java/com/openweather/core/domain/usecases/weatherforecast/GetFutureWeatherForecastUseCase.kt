package com.openweather.core.domain.usecases.weatherforecast

import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mappers.FutureWeatherForecastMapper
import com.openweather.core.domain.models.FutureWeatherForecastModel
import javax.inject.Inject

class GetFutureWeatherForecastUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val futureWeatherForecastMapper: FutureWeatherForecastMapper
) {

    suspend operator fun invoke(): List<FutureWeatherForecastModel> {
        val model = weatherForecastRepository.getFutureWeatherForecast(
            futureWeatherForecastMapper::invoke
        )
        return model
    }
}