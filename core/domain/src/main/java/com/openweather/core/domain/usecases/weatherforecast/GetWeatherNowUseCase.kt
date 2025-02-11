package com.openweather.core.domain.usecases.weatherforecast

import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mapper.WeatherNowMapper
import com.openweather.core.domain.models.WeatherNowModel
import javax.inject.Inject

class GetWeatherNowUseCase @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val weatherNowMapper: WeatherNowMapper
) {

    suspend operator fun invoke(): WeatherNowModel {
        val response = weatherForecastRepository.getWeatherNow()
        val lastWeatherNowData = response.weatherDataList.last()
        return weatherNowMapper(response, lastWeatherNowData)
    }
}