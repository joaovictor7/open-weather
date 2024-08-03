package com.openweather.ui

import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<MainUiState>(MainAnalytic, MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        getInitialData()
    }

    override fun updateWeatherForecast() {
        runAsyncTask {

        }
    }

    private fun getInitialData() {
        updateUiState { it.setSplashScreenFinished() }
    }
}