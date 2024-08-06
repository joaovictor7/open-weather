package com.openweather.core.domain.mapper

import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.data.data.network.responses.WeatherForecastTemperatureResponse
import com.openweather.core.domain.models.FutureDailyWeatherForecastModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.domain.models.WeatherForecastsModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

internal class WeatherForecastMapperTest {

    private val weatherForecastMapper: WeatherForecastMapper = WeatherForecastMapper()
    private val localDateTime = LocalDateTime.now()

    @Test
    fun `mapper test`() {
        val model = weatherForecastMapper(
            todayWeatherForecasts = listOf(
                WeatherForecastDataResponse(
                    dateTime = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond(),
                    temperatureData = WeatherForecastTemperatureResponse(
                        temperature = 22f,
                        minTemperature = 20f,
                        maxTemperature = 25f
                    )
                )
            ),
            futureWeatherForecasts = mapOf(
                localDateTime.toLocalDate() to listOf(
                    WeatherForecastDataResponse(
                        dateTime = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond(),
                        temperatureData = WeatherForecastTemperatureResponse(
                            temperature = 25f,
                            minTemperature = 20f,
                            maxTemperature = 26f
                        )
                    )
                )
            )
        )
        assertEquals(
            WeatherForecastsModel(
                todayWeatherForecast = TodayWeatherForecastModel(
                    minTemperature = 20f,
                    maxTemperature = 25f,
                    temperatures = listOf(22f)
                ),
                futureWeatherForecasts = listOf(
                    FutureWeatherForecastModel(
                        date = localDateTime.toLocalDate(),
                        futureDailyWeatherForecasts = listOf(
                            FutureDailyWeatherForecastModel(
                                iconId = String(),
                                temperature = 25f,
                                dateTime = localDateTime.truncatedTo(ChronoUnit.SECONDS)
                            )
                        )
                    )
                )
            ),
            model
        )
    }
}