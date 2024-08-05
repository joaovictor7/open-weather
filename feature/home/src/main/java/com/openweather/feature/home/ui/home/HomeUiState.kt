package com.openweather.feature.home.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.openweather.core.domain.models.FutureWeatherForecastModel
import com.openweather.core.domain.models.WeatherNowModel
import com.openweather.core.domain.models.WeatherForecastsModel
import com.openweather.core.ui.interfaces.BaseUiState
import com.openweather.feature.home.R
import com.openweather.feature.home.models.TodayWeatherForecastScreenModel
import com.openweather.feature.home.models.WeatherNowViewModel

internal data class HomeUiState(
    val weatherNowModel: WeatherNowViewModel = WeatherNowViewModel(),
    val todayWeatherForecastScreenModels: TodayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(),
    val futureTemperatureForecasts: List<FutureWeatherForecastModel> = emptyList()
) : BaseUiState {

    val weatherNowWithCity: String
        @Composable get() = stringResource(R.string.feature_home_weather_forecast_now)
            .plus(" ${weatherNowModel.city}")

    fun setWeatherNow(weatherNowModel: WeatherNowModel) = copy(
        weatherNowModel = WeatherNowViewModel(
            city = weatherNowModel.city,
            temperature = weatherNowModel.temperature,
            iconId = weatherNowModel.iconId,
            description = weatherNowModel.description
        )
    )

    fun setWeatherForecasts(weatherForecastsModel: WeatherForecastsModel) = copy(
        todayWeatherForecastScreenModels = TodayWeatherForecastScreenModel(
            minTemperature = weatherForecastsModel.todayWeatherForecast.minTemperature,
            maxTemperature = weatherForecastsModel.todayWeatherForecast.maxTemperature,
            temperatures = weatherForecastsModel.todayWeatherForecast.temperatures
        ),
        futureTemperatureForecasts = weatherForecastsModel.futureWeatherForecasts
    )
}
