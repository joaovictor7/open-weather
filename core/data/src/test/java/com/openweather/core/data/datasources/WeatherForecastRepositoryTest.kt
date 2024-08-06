package com.openweather.core.data.datasources

import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherForecastTemperatureResponse
import com.openweather.core.data.data.network.responses.WeatherNowDataResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.data.data.network.responses.WeatherNowTemperatureResponse
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepositoryImpl
import com.openweather.core.test.interfaces.CoroutinesTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId

internal class WeatherForecastRepositoryTest : CoroutinesTest {

    private val localDateTime = LocalDateTime.now()

    override lateinit var testDispatcher: TestDispatcher

    private val openWeatherDataSource: OpenWeatherDataSource = mockk {
        coEvery { getWeatherNow(any()) } returns WeatherNowResponse(
            city = "Porto",
            temperatureData = WeatherNowTemperatureResponse(
                temperature = 22f
            ),
            weatherDataList = listOf(
                WeatherNowDataResponse(
                    icon = String(),
                    description = "céu limpo"
                )
            )
        )
        coEvery { getWeatherForecasts(any()) } returns WeatherForecastResponse(
            dataList = listOf(
                WeatherForecastDataResponse(
                    dateTime = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond(),
                    temperatureData = WeatherForecastTemperatureResponse(
                        temperature = 22f,
                        minTemperature = 20f,
                        maxTemperature = 25f
                    )
                )
            )
        )
    }

    private val weatherForecastRepository: WeatherForecastRepository =
        WeatherForecastRepositoryImpl(
            openWeatherDataSource = openWeatherDataSource,
            buildConfigProvider = mockk(relaxed = true),
            localeProvider = mockk(relaxed = true)
        )

    @Test
    fun `get weather now test`() = runTest(testDispatcher) {
        val response = weatherForecastRepository.getWeatherNow()
        assertEquals(
            WeatherNowResponse(
                city = "Porto",
                temperatureData = WeatherNowTemperatureResponse(
                    temperature = 22f
                ),
                weatherDataList = listOf(
                    WeatherNowDataResponse(
                        icon = String(),
                        description = "céu limpo"
                    )
                )
            ),
            response
        )
    }

    @Test
    fun `get weather forecasts test`() = runTest(testDispatcher) {
        val response = weatherForecastRepository.getWeatherForecasts()
        assertEquals(
            WeatherForecastResponse(
                dataList = listOf(
                    WeatherForecastDataResponse(
                        dateTime = localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond(),
                        temperatureData = WeatherForecastTemperatureResponse(
                            temperature = 22f,
                            minTemperature = 20f,
                            maxTemperature = 25f
                        )
                    )
                )
            ),
            response
        )
    }
}