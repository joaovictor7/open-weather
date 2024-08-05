package com.openweather.core.data.data.repositories.remote

import com.openweather.common.providers.BuildConfigProvider
import com.openweather.common.providers.LocaleProvider
import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val buildConfigProvider: BuildConfigProvider,
    private val localeProvider: LocaleProvider
) : WeatherForecastRepository {

    override suspend fun <T> getWeatherNow(
        mapper: (WeatherNowResponse) -> T
    ): T {
        val response = openWeatherDataSource.getWeatherNow(createRequest())
        return mapper(response)
    }

    override suspend fun <T> getFutureWeatherForecast(
        mapper: (WeatherForecastResponse) -> T
    ): T {
        val response = openWeatherDataSource.getFutureWeatherForecast(createRequest())
        return mapper(response)
    }

    private fun createRequest() = WeatherForecastRequest(
        latitude = PORTO_CITY_LATITUDE,
        longitude = PORTO_CITY_LONGITUDE,
        appId = buildConfigProvider.get.buildConfigFieldsModel.openWeatherApiKey,
        language = localeProvider.currentLanguage,
        metric = "metric"
    )

    private companion object {
        const val PORTO_CITY_LATITUDE = "41.15"
        const val PORTO_CITY_LONGITUDE = "-8.61024"
    }
}