package com.openweather.feature.home.ui.home

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherForecastsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetWeatherNowUseCase
import com.openweather.feature.home.mapper.FutureWeatherForecastScreenModelsMapper
import com.openweather.feature.home.mapper.WeatherNowScreenModelMapper
import com.openweather.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getWeatherNowUseCase: GetWeatherNowUseCase,
    private val getWeatherForecastsUseCase: GetWeatherForecastsUseCase,
    private val futureWeatherForecastScreenModelsMapper: FutureWeatherForecastScreenModelsMapper,
    private val weatherNowScreenModelMapper: WeatherNowScreenModelMapper,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
        getWeatherForecastData()
    }

    override fun refresh() {
        getWeatherForecastData()
    }

    private fun getWeatherForecastData() {
        runAsyncTask(
            onStart = { updateUiState { it.setLoading(true) } },
            onCompletion = { updateUiState { it.setLoading(false) } }
        ) {
            launch { setWeatherNow() }
            launch { setWeatherForecasts() }
        }
    }

    private suspend fun setWeatherNow() {
        val weatherNowForecast = getWeatherNowUseCase()
        updateUiState {
            it.setWeatherNow(weatherNowScreenModelMapper(weatherNowForecast))
        }
    }

    private suspend fun setWeatherForecasts() {
        val weatherForecast = getWeatherForecastsUseCase()
        updateUiState {
            it.setWeatherForecasts(
                weatherForecast.todayWeatherForecast,
                futureWeatherForecastScreenModelsMapper(weatherForecast.futureWeatherForecasts)
            )
        }
    }
}