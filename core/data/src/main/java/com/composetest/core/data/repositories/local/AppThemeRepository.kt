package com.composetest.core.data.repositories.local

import com.composetest.common.enums.Theme
import com.composetest.common.models.AppThemeModel
import kotlinx.coroutines.flow.StateFlow

interface AppThemeRepository {
    val appThemeState: StateFlow<AppThemeModel>

    suspend fun setTheme(theme: Theme)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun setCustomTheme(customTheme: Theme?)
    suspend fun getAppThemeFromDataStore()
}