package com.openweather.core.domain.managers

import com.openweather.common.enums.Theme
import com.openweather.common.models.AppThemeModel
import kotlinx.coroutines.flow.Flow

interface AppThemeManager {

    fun getAppTheme(): Flow<AppThemeModel>

    suspend fun setTheme(theme: Theme)

    suspend fun setDynamicColor(dynamicColor: Boolean)

    fun setCustomTheme(customTheme: Theme?)
}