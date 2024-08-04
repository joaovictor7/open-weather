package com.openweather.feature.home.ui.home

import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetFutureWeatherForecastUseCase
import com.openweather.core.domain.usecases.weatherforecast.GetTodayWeatherForecastUseCase
import com.openweather.feature.home.ui.home.analytics.HomeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getTodayWeatherForecastUseCase: GetTodayWeatherForecastUseCase,
    private val getFutureWeatherForecastUseCase: GetFutureWeatherForecastUseCase,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<HomeUiState>(HomeAnalytic, HomeUiState()), HomeCommandReceiver {

    override val commandReceiver = this

    init {
        openScreenAnalytic()
    }
}