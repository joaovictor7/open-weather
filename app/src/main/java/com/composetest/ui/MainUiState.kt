package com.composetest.ui

import com.composetest.common.models.AppThemeModel

data class MainUiState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val showSplashScreen: Boolean = true
) {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)
    fun splashScreenFinished() = copy(showSplashScreen = false)
}
