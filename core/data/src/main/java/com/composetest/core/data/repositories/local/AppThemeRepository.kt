package com.composetest.core.data.repositories.local

import com.composetest.common.enums.Theme
import com.composetest.common.models.AppThemeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AppThemeRepository {
    val appThemeState: StateFlow<AppThemeModel>
    val currentAppTheme: AppThemeModel

    suspend fun setTheme(theme: Theme)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun setCustomTheme(customTheme: Theme?)
    fun <T> getAppTheme(converter: (String?, Boolean?) -> T): Flow<T>
}