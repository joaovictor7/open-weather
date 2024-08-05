package com.openweather.feature.home.ui.home

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherForecastsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherNowUseCase
import com.openweather.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getWeatherNowUseCase: GetWeatherNowUseCase,
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        initState()
    }

    private fun initState() {
        runAsyncTask {
            setTodayWeather()
            setWeatherForecast()
        }
    }

    private suspend fun setTodayWeather() {
        val todayWeatherForecast = getWeatherNowUseCase()
        updateUiState {
            it.setWeatherNow(todayWeatherForecast)
        }
    }

    private suspend fun setWeatherForecast() {
        val weatherForecast = getWeatherForecastsUseCase()
        updateUiState {
            it.setWeatherForecasts(weatherForecast)
        }
    }
}