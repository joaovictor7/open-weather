package com.openweather.feature.home.mapper

import com.openweather.core.domain.models.FutureDailyWeatherForecastModel
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.feature.home.models.FutureDailyWeatherForecastScreenModel
import com.openweather.feature.home.models.FutureWeatherForecastScreenModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class WeatherNowScreenModelMapperTest {

    private val localDateMock = LocalDate.parse("2024-08-05")
    private val dateToHourFormatter = DateTimeFormatter.ofPattern("HH")
    private val futureWeatherForecastScreenModelsMapper = FutureWeatherForecastScreenModelsMapper()

    @Test
    fun `mapper test`() {
        val model = futureWeatherForecastScreenModelsMapper(
            listOf(
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
        assertEquals(
            listOf(
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
            ),
            model
        )
    }
}