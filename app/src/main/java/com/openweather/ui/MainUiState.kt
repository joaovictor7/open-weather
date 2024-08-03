package com.openweather.ui

import com.openweather.core.ui.interfaces.BaseUiState

data class MainUiState(
    val showSplashScreen: Boolean = true
) : BaseUiState {
    fun setSplashScreenFinished() = copy(showSplashScreen = false)
}
