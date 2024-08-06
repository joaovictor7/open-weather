package com.openweather.core.domain.mapper

import com.openweather.core.data.data.network.responses.WeatherNowDataResponse
import com.openweather.core.data.data.network.responses.WeatherNowResponse
import com.openweather.core.data.data.network.responses.WeatherNowTemperatureResponse
import com.openweather.core.domain.models.WeatherNowModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class WeatherNowMapperTest {

    private val weatherNowMapper: WeatherNowMapper = WeatherNowMapper()

    @Test
    fun `mapper test`() {
        val model = weatherNowMapper(
            response = WeatherNowResponse(
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
            weatherNowDataResponse = WeatherNowDataResponse(
                icon = String(),
                description = "céu limpo"
            )
        )
        assertEquals(
            WeatherNowModel(
                city = "Porto",
                temperature = 22f,
                iconId = String(),
                description = "céu limpo"
            ),
            model
        )
    }
}