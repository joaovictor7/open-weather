package com.openweather.core.data.data.repositories.remote

import com.openweather.common.providers.BuildConfigProvider
import com.openweather.common.providers.LocaleProvider
import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import javax.inject.Inject

internal class WeatherForecastRepositoryImpl @Inject constructor(
    private val openWeatherDataSource: OpenWeatherDataSource,
    private val buildConfigProvider: BuildConfigProvider,
    private val localeProvider: LocaleProvider
) : WeatherForecastRepository {

    override suspend fun getWeatherNow() =
        openWeatherDataSource.getWeatherNow(createRequest())

    override suspend fun getWeatherForecasts() =
        openWeatherDataSource.getWeatherForecasts(createRequest())

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