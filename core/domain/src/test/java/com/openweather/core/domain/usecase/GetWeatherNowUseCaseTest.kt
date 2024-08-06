package com.openweather.core.domain.usecase

import com.openweather.core.data.data.network.responses.WeatherNowDataResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.data.data.network.responses.WeatherNowTemperatureResponse
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mapper.WeatherNowMapper
import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherNowUseCase
import com.openweather.core.test.interfaces.CoroutinesTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GetWeatherNowUseCaseTest : CoroutinesTest {

    private val weatherNowMapper: WeatherNowMapper = WeatherNowMapper()
    private val weatherForecastRepository: WeatherForecastRepository = mockk {
        coEvery { getWeatherNow() } coAnswers {
            withContext(testDispatcher) {
                WeatherNowResponse(
                    city = "Porto",
                    temperatureData = WeatherNowTemperatureResponse(temperature = 20f),
                    weatherDataList = listOf(
                        WeatherNowDataResponse(icon = String(), description = "céu limpo")
                    )
                )
            }
        }
    }
    private val getWeatherNowUseCase: GetWeatherNowUseCase = GetWeatherNowUseCase(
        weatherNowMapper = weatherNowMapper,
        weatherForecastRepository = weatherForecastRepository
    )

    override lateinit var testDispatcher: TestDispatcher

    @Test
    fun `get weather now test`() = runTest(testDispatcher) {
        val model = getWeatherNowUseCase()
        assertEquals(
            WeatherNowModel(
                city = "Porto",
                temperature = 20f,
                iconId = String(),
                description = "céu limpo"
            ),
            model
        )
    }
}