package com.composetest.ui

import com.composetest.core.designsystem.domain.bases.BaseState
import com.composetest.core.designsystem.domain.models.AppThemeModel

data class MainState(
    val appTheme: AppThemeModel = AppThemeModel()
): BaseState {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun initState(appTheme: AppThemeModel) = copy(appTheme = appTheme)
}
