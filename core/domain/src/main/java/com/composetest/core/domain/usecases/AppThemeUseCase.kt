package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.flow.StateFlow

interface AppThemeUseCase  {

    val appThemeState: StateFlow<AppThemeModel>

    val currentAppTheme: AppThemeModel

    suspend fun setAppTheme(theme: Theme)

    suspend fun setDynamicColors(dynamicColors: Boolean)

    fun setCustomTheme(customTheme: Theme?)
}