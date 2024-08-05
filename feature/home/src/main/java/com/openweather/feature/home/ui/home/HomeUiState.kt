package com.openweather.feature.home.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.openweather.core.domain.models.TodayWeatherForecastModel
import com.openweather.core.ui.interfaces.BaseUiState
import com.openweather.feature.home.R
import com.openweather.feature.home.models.FutureWeatherForecastScreenModel
import com.openweather.feature.home.models.TodayWeatherForecastScreenModel
import com.openweather.feature.home.models.WeatherNowScreenModel

internal data class HomeUiState(
    val weatherNowModel: WeatherNowScreenModel = WeatherNowScreenModel(),
    val todayWeatherForecastScreenModel: TodayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(),
    val futureWeatherForecastScreenModels: List<FutureWeatherForecastScreenModel> = emptyList()
) : BaseUiState {

    val weatherNowWithCity: String
        @Composable get() = stringResource(R.string.feature_home_weather_forecast_now)
            .plus(" ${weatherNowModel.city}")

    fun setWeatherNow(weatherNowModel: WeatherNowScreenModel) = copy(
        weatherNowModel = weatherNowModel
    )

    fun setWeatherForecasts(
        todayWeatherForecastModel: TodayWeatherForecastModel,
        futureWeatherForecastScreenModels: List<FutureWeatherForecastScreenModel>
    ) = copy(
        todayWeatherForecastScreenModel = TodayWeatherForecastScreenModel(
            minTemperature = todayWeatherForecastModel.minTemperature,
            maxTemperature = todayWeatherForecastModel.maxTemperature,
            temperatures = todayWeatherForecastModel.temperatures
        ),
        futureWeatherForecastScreenModels = futureWeatherForecastScreenModels
    )
}
