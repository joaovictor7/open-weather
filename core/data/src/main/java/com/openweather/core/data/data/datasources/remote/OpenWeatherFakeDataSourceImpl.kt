package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherResponse
import com.openweather.core.data.models.responses.weatherforecast.TodayWeatherForecastResponse
import com.openweather.core.data.models.responses.weatherforecast.ForecastTemperatureDataResponse
import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastDataResponse
import com.openweather.core.data.models.responses.weatherforecast.FutureWeatherForecastResponse

internal class OpenWeatherFakeDataSourceImpl(
    private val remoteCallManager: RemoteCallManager
) : OpenWeatherDataSource {

    override suspend fun getCurrentWeatherForecast() = remoteCallManager.safeRemoteCall {
        TodayWeatherForecastResponse(
            cityName = "Porto",
            temperatureData = ForecastTemperatureDataResponse(
                temperature = 2.0
            ),
            weather = listOf(
                TodayWeatherResponse(
                    icon = "0",
                    "Céu limpo"
                )
            )
        )
    }

    override suspend fun getFutureWeatherForecast() = remoteCallManager.safeRemoteCall {
        FutureWeatherForecastResponse(
            forecastList = listOf(
                FutureWeatherForecastDataResponse(
                    dateTime = 1f,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2.0
                    )
                ),
                FutureWeatherForecastDataResponse(
                    dateTime = 1f,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2.0
                    )
                ),
                FutureWeatherForecastDataResponse(
                    dateTime = 1f,
                    temperatureData = ForecastTemperatureDataResponse(
                        temperature = 2.0
                    )
                )
            )
        )
    }
}