package com.openweather.core.data.data.repositories.local

import com.openweather.common.enums.Theme
import com.openweather.core.data.constants.preferencesdatastore.PreferencesDataKeys
import com.openweather.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.openweather.core.data.mappers.AppThemeModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class AppThemeRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSourceImpl,
    private val appThemeModelMapper: AppThemeModelMapper,
) : AppThemeRepository {

    private val _customAppTheme = MutableStateFlow<Theme?>(null)
    override val customAppTheme = _customAppTheme.asStateFlow()

    override suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme.name)
    }

    override suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }

    override fun setCustomTheme(customTheme: Theme?) {
        _customAppTheme.update { customTheme }
    }

    override fun getAppTheme() = preferenceDataSource.getData { preferences ->
        val theme = preferences[PreferencesDataKeys.appTheme]
        val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
        appThemeModelMapper.invoke(theme, dynamicColors)
    }
}