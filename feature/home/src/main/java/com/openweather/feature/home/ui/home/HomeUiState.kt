package com.openweather.feature.home.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.ui.interfaces.BaseUiState
import com.openweather.feature.home.R
import com.openweather.feature.home.models.FutureDayForecast

internal data class HomeUiState(
    val weatherNow: String = String(),
    val city: String = String(),
    val temperatureNow: String = String(),
    val maxTemperature: Float = 0f,
    val minTemperature: Float = 0f,
    val futureForecasts: List<FutureDayForecast> = emptyList()
) : BaseUiState {

    val weatherNowWithCity: String
        @Composable get() = stringResource(R.string.feature_home_weather_forecast_now)
            .plus(" $city")

    fun setCurrentWeatherForecast(
        todayWeatherForecastModel: TodayWeatherForecastModel
    ) = copy(
        weatherNow = todayWeatherForecastModel.weatherDescription,
        city = todayWeatherForecastModel.cityName,
        temperatureNow = todayWeatherForecastModel.temperature,
        maxTemperature = todayWeatherForecastModel.maxTemperature,
        minTemperature = todayWeatherForecastModel.minTemperature
    )
}
