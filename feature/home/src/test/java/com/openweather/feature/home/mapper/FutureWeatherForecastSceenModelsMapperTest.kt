package com.openweather.feature.home.mapper

import com.openweather.common.providers.LocaleProvider
import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.feature.home.models.WeatherNowScreenModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.Locale

internal class FutureWeatherForecastSceenModelsMapperTest {

    private val weatherForecastScreenModelsMapper = WeatherNowScreenModelMapper(
        localeProvider = object : LocaleProvider {
            override val default: Locale = Locale.getDefault()
            override val currentLanguage: String = Locale.getDefault().language
        }
    )

    @Test
    fun `mapper test`() {
        val model = weatherForecastScreenModelsMapper(
            WeatherNowModel(
                city = "Porto",
                temperature = 20f,
                iconId = String(),
                description = "céu limpo"
            )
        )
        assertEquals(
            WeatherNowScreenModel(
                city = "Porto",
                temperature = "20º",
                iconId = String(),
                description = "Céu limpo"
            ),
            model
        )
    }
}