package com.composetest.core.data.data.repositories.local

import com.composetest.common.enums.Theme
import com.composetest.common.models.AppThemeModel
import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    val customAppTheme: Flow<Theme?>

    suspend fun setTheme(theme: Theme)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun setCustomTheme(customTheme: Theme?)
    fun getAppTheme(): Flow<AppThemeModel>
}