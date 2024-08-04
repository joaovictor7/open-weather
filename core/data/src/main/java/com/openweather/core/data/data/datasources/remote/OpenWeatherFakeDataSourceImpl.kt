package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.data.network.responses.TodayWeatherResponse
import com.openweather.core.data.data.network.responses.TodayWeatherForecastResponse
import com.openweather.core.data.data.network.responses.ForecastTemperatureDataResponse
import com.openweather.core.data.data.network.responses.FutureWeatherForecastDataResponse
import com.openweather.core.data.data.network.responses.FutureWeatherForecastResponse

internal class OpenWeatherFakeDataSourceImpl(
    private val remoteCallManager: RemoteCallManager
) : OpenWeatherDataSource {

    override suspend fun getCurrentWeatherForecast(
        request: WeatherForecastRequest
    ) = remoteCallManager.safeRemoteCall {
        TodayWeatherForecastResponse(
            cityName = "Porto",
            temperatureData = ForecastTemperatureDataResponse(
                temperature = 2f,
                minTemperature = 1f,
                maxTemperature = 3f
            ),
            weather = listOf(
                TodayWeatherResponse(
                    icon = "0",
                    "Céu limpo"
                )
            )
        )
    }

    override suspend fun getFutureWeatherForecast(
        request: WeatherForecastRequest
    ) = remoteCallManager.safeRemoteCall {
        FutureWeatherForecastResponse(
            forecastList = listOf(
                FutureWeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                ),
                FutureWeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                ),
                FutureWeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                )
            )
        )
    }
}