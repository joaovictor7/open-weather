package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.extensions.get
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder

internal class OpenWeatherDataSourceImpl(
    private val safeRemoteCallManager: RemoteCallManager,
    private val openWeatherApi: HttpClient
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(request: WeatherForecastRequest) =
        safeRemoteCallManager.safeRemoteCall {
            openWeatherApi.get<WeatherNowResponse>(WEATHER_URL) {
                appendParameters(request)
            }
        }

    override suspend fun getFutureWeatherForecast(
        request: WeatherForecastRequest
    ) = safeRemoteCallManager.safeRemoteCall {
        openWeatherApi.get<WeatherForecastResponse>(FORECAST_URL) {
            appendParameters(request)
        }
    }

    private fun HttpRequestBuilder.appendParameters(request: WeatherForecastRequest) {
        url {
            with(parameters) {
                append("lat", request.latitude)
                append("lon", request.longitude)
                append("appid", request.appId)
                append("lang", request.language)
                append("units", request.metric)
            }
        }
    }

    private companion object {
        const val WEATHER_URL = "weather"
        const val FORECAST_URL = "forecast"
    }
}
