package com.composetest.core.ui.providers

import com.composetest.core.ui.domain.enums.AppTheme
import com.composetest.core.ui.domain.models.AppThemeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppThemeProvider @Inject constructor() {

    private val _appThemeState = MutableStateFlow(AppThemeModel())
    val appThemeState = _appThemeState.asStateFlow()

    val currentAppTheme get() = appThemeState.value

    fun setAppTheme(theme: AppTheme) = _appThemeState.update { it.copy(theme = theme) }

    fun setDynamicColors(dynamicColors: Boolean) =
        _appThemeState.update { it.copy(dynamicColors = dynamicColors) }

    fun setCustomTheme(customTheme: AppTheme?) =
        _appThemeState.update { it.copy(customTheme = customTheme) }
}