package com.openweather.feature.home.viewmodel

import com.openweather.common.providers.LocaleProvider
import com.openweather.core.domain.models.FutureDailyWeatherForecastModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.domain.models.WeatherForecastsModel
import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherForecastsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherNowUseCase
import com.openweather.core.test.interfaces.CoroutinesTest
import com.openweather.core.test.utils.runStateFlowTest
import com.openweather.feature.home.mapper.FutureWeatherForecastScreenModelsMapper
import com.openweather.feature.home.mapper.WeatherNowScreenModelMapper
import com.openweather.feature.home.models.FutureDailyWeatherForecastScreenModel
import com.openweather.feature.home.models.FutureWeatherForecastScreenModel
import com.openweather.feature.home.models.TodayWeatherForecastScreenModel
import com.openweather.feature.home.models.WeatherNowScreenModel
import com.openweather.feature.home.ui.home.HomeCommands
import com.openweather.feature.home.ui.home.HomeUiState
import com.openweather.feature.home.ui.home.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

internal class HomeViewModelTest : CoroutinesTest {

    private val localDateMock = LocalDate.parse("2024-08-05")
    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val weatherNowScreenModelMapper = WeatherNowScreenModelMapper(
        localeProvider = object : LocaleProvider {
            override val default: Locale = Locale.getDefault()
            override val currentLanguage: String = Locale.getDefault().language
        }
    )
    private val futureWeatherForecastScreenModelsMapper = FutureWeatherForecastScreenModelsMapper()
    private val analyticsUseCase: AnalyticsUseCase = mockk(relaxed = true)
    private val getWeatherNowUseCase: GetWeatherNowUseCase = mockk {
        coEvery { this@mockk.invoke() } coAnswers {
            withContext(testDispatcher) {
                WeatherNowModel(
                    city = "Porto",
                    temperature = 20f,
                    iconId = String(),
                    description = "céu limpo"
                )
            }
        }
    }
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase = mockk {
        coEvery { this@mockk.invoke() } returns WeatherForecastsModel(
            todayWeatherForecast = TodayWeatherForecastModel(
                minTemperature = 15f,
                maxTemperature = 25f,
                temperatures = listOf(15f, 17f, 24f, 25f)
            ),
            futureWeatherForecasts = listOf(
                FutureWeatherForecastModel(
                    date = localDateMock,
                    futureDailyWeatherForecasts = listOf(
                        FutureDailyWeatherForecastModel(
                            iconId = String(),
                            temperature = 20f,
                            dateTime = LocalDateTime.now()
                        )
                    )
                )
            )
        )
    }

    private lateinit var viewModel: HomeViewModel

    override lateinit var testDispatcher: TestDispatcher

    @BeforeEach
    fun before() {
        viewModel = HomeViewModel(
            getWeatherForecastsUseCase = getWeatherForecastsUseCase,
            getWeatherNowUseCase = getWeatherNowUseCase,
            futureWeatherForecastScreenModelsMapper = futureWeatherForecastScreenModelsMapper,
            weatherNowScreenModelMapper = weatherNowScreenModelMapper,
            analyticsUseCase = analyticsUseCase,
        )
    }

    @Test
    fun `get all weather forecasts`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedUiStates ->
            job.cancel()
            assertEquals(
                listOf(
                    HomeUiState(
                        weatherNowModel = WeatherNowScreenModel(
                            city = "Porto",
                            temperature = "20º",
                            iconId = String(),
                            description = "Céu limpo"
                        ),
                        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                            minTemperature = 15f,
                            maxTemperature = 25f,
                            temperatures = listOf(15f, 17f, 24f, 25f)
                        ),
                        futureWeatherForecastScreenModels = listOf(
                            FutureWeatherForecastScreenModel(
                                day = "segunda-feira, 05 agosto 2024",
                                futureDailyWeatherForecasts = listOf(
                                    FutureDailyWeatherForecastScreenModel(
                                        iconId = String(),
                                        temperature = "20º",
                                        hour = "${LocalDateTime.now().format(dateToHourFormatter)}h"
                                    )
                                )
                            )
                        )
                    )
                ),
                collectedUiStates
            )
        }

    @Test
    fun `refresh page test`() =
        runStateFlowTest(testDispatcher, viewModel.uiState) { job, collectedUiStates ->
            coEvery { getWeatherNowUseCase() } returns WeatherNowModel(
                city = "Lisboa",
                temperature = 22f,
                iconId = String(),
                description = "céu limpo"
            )
            coEvery { getWeatherForecastsUseCase() } returns WeatherForecastsModel(
                todayWeatherForecast = TodayWeatherForecastModel(
                    minTemperature = 15f,
                    maxTemperature = 25f,
                    temperatures = listOf(15f, 17f, 24f, 25f)
                ),
                futureWeatherForecasts = listOf(
                    FutureWeatherForecastModel(
                        date = localDateMock,
                        futureDailyWeatherForecasts = listOf(
                            FutureDailyWeatherForecastModel(
                                iconId = String(),
                                temperature = 20f,
                                dateTime = LocalDateTime.now()
                            )
                        )
                    )
                )
            )

            viewModel.executeCommand(HomeCommands.Refresh)
            job.cancel()

            assertEquals(
                listOf(
                    HomeUiState(
                        weatherNowModel = WeatherNowScreenModel(
                            city = "Porto",
                            temperature = "20º",
                            iconId = String(),
                            description = "Céu limpo"
                        ),
                        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                            minTemperature = 15f,
                            maxTemperature = 25f,
                            temperatures = listOf(15f, 17f, 24f, 25f)
                        ),
                        futureWeatherForecastScreenModels = listOf(
                            FutureWeatherForecastScreenModel(
                                day = "segunda-feira, 05 agosto 2024",
                                futureDailyWeatherForecasts = listOf(
                                    FutureDailyWeatherForecastScreenModel(
                                        iconId = String(),
                                        temperature = "20º",
                                        hour = "${LocalDateTime.now().format(dateToHourFormatter)}h"
                                    )
                                )
                            )
                        )
                    ),
                    HomeUiState(
                        isLoading = true,
                        weatherNowModel = WeatherNowScreenModel(
                            city = "Porto",
                            temperature = "20º",
                            iconId = String(),
                            description = "Céu limpo"
                        ),
                        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                            minTemperature = 15f,
                            maxTemperature = 25f,
                            temperatures = listOf(15f, 17f, 24f, 25f)
                        ),
                        futureWeatherForecastScreenModels = listOf(
                            FutureWeatherForecastScreenModel(
                                day = "segunda-feira, 05 agosto 2024",
                                futureDailyWeatherForecasts = listOf(
                                    FutureDailyWeatherForecastScreenModel(
                                        iconId = String(),
                                        temperature = "20º",
                                        hour = "${LocalDateTime.now().format(dateToHourFormatter)}h"
                                    )
                                )
                            )
                        )
                    ),
                    HomeUiState(
                        isLoading = true,
                        weatherNowModel = WeatherNowScreenModel(
                            city = "Lisboa",
                            temperature = "22º",
                            iconId = String(),
                            description = "Céu limpo"
                        ),
                        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                            minTemperature = 15f,
                            maxTemperature = 25f,
                            temperatures = listOf(15f, 17f, 24f, 25f)
                        ),
                        futureWeatherForecastScreenModels = listOf(
                            FutureWeatherForecastScreenModel(
                                day = "segunda-feira, 05 agosto 2024",
                                futureDailyWeatherForecasts = listOf(
                                    FutureDailyWeatherForecastScreenModel(
                                        iconId = String(),
                                        temperature = "20º",
                                        hour = "${LocalDateTime.now().format(dateToHourFormatter)}h"
                                    )
                                )
                            )
                        )
                    ),
                    HomeUiState(
                        isLoading = false,
                        weatherNowModel = WeatherNowScreenModel(
                            city = "Lisboa",
                            temperature = "22º",
                            iconId = String(),
                            description = "Céu limpo"
                        ),
                        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
                            minTemperature = 15f,
                            maxTemperature = 25f,
                            temperatures = listOf(15f, 17f, 24f, 25f)
                        ),
                        futureWeatherForecastScreenModels = listOf(
                            FutureWeatherForecastScreenModel(
                                day = "segunda-feira, 05 agosto 2024",
                                futureDailyWeatherForecasts = listOf(
                                    FutureDailyWeatherForecastScreenModel(
                                        iconId = String(),
                                        temperature = "20º",
                                        hour = "${LocalDateTime.now().format(dateToHourFormatter)}h"
                                    )
                                )
                            )
                        )
                    ),
                ),
                collectedUiStates
            )
        }
}