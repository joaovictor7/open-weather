package com.openweather.core.domain.usecase

import com.openweather.common.extensions.unixToLocalDateTime
import com.openweather.common.providers.DateTimeProvider
import com.openweather.core.data.data.network.responses.WeatherForecastDataResponse
import com.openweather.core.data.data.network.responses.WeatherForecastResponse
import com.openweather.core.data.data.network.responses.WeatherForecastTemperatureResponse
import com.openweather.core.data.data.repositories.remote.WeatherForecastRepository
import com.openweather.core.domain.mapper.WeatherForecastMapper
import com.openweather.core.domain.models.FutureDailyWeatherForecastModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.domain.models.WeatherForecastsModel
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherForecastsUseCase
import com.openweather.core.test.interfaces.CoroutinesTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

private const val unitNowDateTime = 1723248000L
private const val unitFutureDateTime = 1722985200L

internal class GetWeatherForecastsUseCaseTest : CoroutinesTest {
    private val localDateTimeNowMock = (unitNowDateTime).unixToLocalDateTime
    private val localDateTimeFutureMock = (unitFutureDateTime).unixToLocalDateTime
    private val weatherForecastMapper: WeatherForecastMapper = WeatherForecastMapper()
    private val dateTimeProvider: DateTimeProvider = object : DateTimeProvider {
        override val nowDateTime: LocalDateTime = localDateTimeNowMock
        override val nowDateTimeInSeconds: Long = 0

    }
    private val weatherForecastRepository: WeatherForecastRepository = mockk {
        coEvery { getWeatherForecasts() } coAnswers {
            withContext(testDispatcher) {
                WeatherForecastResponse(
                    dataList = listOf(
                        WeatherForecastDataResponse(
                            dateTime = unitNowDateTime,
                            temperatureData = WeatherForecastTemperatureResponse(
                                temperature = 20f,
                                minTemperature = 2f,
                                maxTemperature = 22f
                            )
                        ),
                        WeatherForecastDataResponse(
                            dateTime = unitFutureDateTime,
                            temperatureData = WeatherForecastTemperatureResponse(
                                temperature = 22f,
                                minTemperature = 2f,
                                maxTemperature = 22f
                            )
                        )
                    )
                )
            }
        }
    }
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase = GetWeatherForecastsUseCase(
        weatherForecastRepository = weatherForecastRepository,
        weatherForecastMapper = weatherForecastMapper,
        dateTimeProvider = dateTimeProvider
    )

    override lateinit var testDispatcher: TestDispatcher

    @Test
    fun `get weather forecasts test`() = runTest(testDispatcher) {
        val model = getWeatherForecastsUseCase()
        assertEquals(
            WeatherForecastsModel(
                todayWeatherForecast = TodayWeatherForecastModel(
                    minTemperature = 2f,
                    maxTemperature = 22f,
                    temperatures = listOf(20f, 22f)
                ),
                futureWeatherForecasts = listOf(
                    FutureWeatherForecastModel(
                        date = localDateTimeFutureMock.toLocalDate(),
                        futureDailyWeatherForecasts = listOf(
                            FutureDailyWeatherForecastModel(
                                iconId = String(),
                                temperature = 22f,
                                dateTime = localDateTimeFutureMock
                            )
                        )
                    )
                )
            ),
            model
        )
    }
}