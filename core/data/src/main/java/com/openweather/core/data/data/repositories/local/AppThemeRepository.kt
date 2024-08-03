package com.openweather.core.data.data.repositories.local

import com.openweather.common.enums.Theme
import com.openweather.common.models.AppThemeModel
import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    val customAppTheme: Flow<Theme?>

    suspend fun setTheme(theme: Theme)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun setCustomTheme(customTheme: Theme?)
    fun getAppTheme(): Flow<AppThemeModel>
}