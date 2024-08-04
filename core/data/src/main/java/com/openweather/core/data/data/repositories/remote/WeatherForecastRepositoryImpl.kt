package com.openweather.core.data.data.repositories.remote

import com.openweather.common.providers.BuildConfigProvider
import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.data.network.responses.FutureWeatherForecastResponse
import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse
import java.util.Locale
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val buildConfigProvider: BuildConfigProvider
) : WeatherForecastRepository {

    override suspend fun <T> getTodayWeatherForecast(
        mapper: (TodayWeatherForecastResponse) -> T
    ): T {
        val response = openWeatherDataSource.getCurrentWeatherForecast(createRequest())
        return mapper(response)
    }

    override suspend fun <T> getFutureWeatherForecast(
        mapper: (FutureWeatherForecastResponse) -> T
    ): T {
        val response = openWeatherDataSource.getFutureWeatherForecast(createRequest())
        return mapper(response)
    }

    private fun createRequest() = WeatherForecastRequest(
        latitude = PORTO_CITY_LATITUDE,
        longitude = PORTO_CITY_LONGITUDE,
        appId = buildConfigProvider.get.buildConfigFieldsModel.openWeatherApiKey,
        language = Locale.getDefault().language,
        metric = "metric"
    )

    private companion object {
        const val PORTO_CITY_LATITUDE = "41.15"
        const val PORTO_CITY_LONGITUDE = "-8.61024"
    }
}