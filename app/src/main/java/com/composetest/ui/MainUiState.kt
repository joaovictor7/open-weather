package com.composetest.ui

import com.composetest.common.models.AppThemeModel

data class MainUiState(
    val appTheme: AppThemeModel = AppThemeModel()
) {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)
}
