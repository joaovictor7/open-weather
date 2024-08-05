package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.data.network.requests.WeatherForecastRequest
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.data.network.responses.WeatherNowDataResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.data.data.network.responses.WeatherNowTemperatureResponse
import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherForecastTemperatureResponse

internal class OpenWeatherFakeDataSourceImpl(
    private val remoteCallManager: RemoteCallManager
) : OpenWeatherDataSource {

    override suspend fun getWeatherNow(
        request: WeatherForecastRequest
    ) = remoteCallManager.safeRemoteCall {
        WeatherNowResponse(
            city = "Porto",
            temperatureData = WeatherNowTemperatureResponse(
                temperature = 2f
            ),
            weatherDataList = listOf(
                WeatherNowDataResponse(
                    icon = "0",
                    "Céu limpo"
                )
            )
        )
    }

    override suspend fun getFutureWeatherForecast(
        request: WeatherForecastRequest
    ) = remoteCallManager.safeRemoteCall {
        WeatherForecastResponse(
            dataList = listOf(
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = WeatherForecastTemperatureResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                ),
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = WeatherForecastTemperatureResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                ),
                WeatherForecastDataResponse(
                    dateTime = 1,
                    temperatureData = WeatherForecastTemperatureResponse(
                        temperature = 2f,
                        minTemperature = 1f,
                        maxTemperature = 5f
                    )
                )
            )
        )
    }
}