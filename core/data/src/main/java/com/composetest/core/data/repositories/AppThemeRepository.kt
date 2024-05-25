package com.composetest.core.data.repositories

import kotlinx.coroutines.flow.Flow

interface AppThemeRepository {
    suspend fun setTheme(theme: String)
    suspend fun setDynamicColor(dynamicColor: Boolean)
    fun <T> getAppTheme(converter: (String?, Boolean?) -> T): Flow<T>
}