package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.extensions.get
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherForecastResponse
import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder

internal class OpenWeatherDataSourceImpl(
    private val safeRemoteCallManager: RemoteCallManager,
    private val openWeatherApi: HttpClient
) : OpenWeatherDataSource {

    override suspend fun getCurrentWeatherForecast() = safeRemoteCallManager.safeRemoteCall {
        openWeatherApi.get<TodayWeatherForecastResponse>(WEATHER_URL) {
            appendParameters()
        }
    }

    override suspend fun getFutureWeatherForecast() = safeRemoteCallManager.safeRemoteCall {
        openWeatherApi.get<FutureWeatherForecastResponse>(FORECAST_URL) {
            appendParameters()
        }
    }

    private fun HttpRequestBuilder.appendParameters() {
        url {
            with(parameters) {
                append("lat", "41.15")
                append("lon", "-8.61024")
                append("appid", "8fbe2064ecb71fc9bc7d9bb6a4244818")
                append("lang", "pt")
                append("units", "metric")
            }
        }
    }

    private companion object {
        const val WEATHER_URL = "weather"
        const val FORECAST_URL = "forecast"
    }
}
