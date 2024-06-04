package com.composetest.ui

import com.composetest.core.designsystem.ui.bases.BaseState
import com.composetest.core.domain.models.AppThemeModel

data class MainState(
    val appTheme: AppThemeModel = AppThemeModel()
): BaseState {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)
}
