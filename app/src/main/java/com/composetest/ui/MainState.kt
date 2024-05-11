package com.composetest.ui

import androidx.activity.SystemBarStyle
import com.composetest.core.ui.domain.models.AppThemeModel

data class MainState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val statusBarStyle: SystemBarStyle? = null,
    val navigationBarStyle: SystemBarStyle? = null
) {
    fun initState(
        appTheme: AppThemeModel,
        statusBarStyle: SystemBarStyle,
        navigationBarStyle: SystemBarStyle
    ) = copy(
        appTheme = appTheme,
        statusBarStyle = statusBarStyle,
        navigationBarStyle = navigationBarStyle
    )
}
