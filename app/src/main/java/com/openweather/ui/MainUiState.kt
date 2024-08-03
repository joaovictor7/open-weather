package com.openweather.ui

import com.openweather.common.models.AppThemeModel
import com.openweather.core.ui.interfaces.BaseUiState

data class MainUiState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val showSplashScreen: Boolean = true
) : BaseUiState {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)
    fun splashScreenFinished() = copy(showSplashScreen = false)
}
