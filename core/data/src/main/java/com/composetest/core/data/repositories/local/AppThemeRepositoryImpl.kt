package com.composetest.core.data.repositories.local

import com.composetest.common.enums.Theme
import com.composetest.common.models.AppThemeModel
import com.composetest.core.data.constants.preferencesdatastore.PreferencesDataKeys
import com.composetest.core.data.datasources.local.PreferenceDataSourceImpl
import com.composetest.core.data.mappers.AppThemeModelMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppThemeRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSourceImpl,
    private val appThemeModelMapper: AppThemeModelMapper
) : AppThemeRepository {

    private val _appThemeState = MutableStateFlow(AppThemeModel())
    override val appThemeState = _appThemeState.asStateFlow()

    override suspend fun setTheme(theme: Theme) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme.name)
        _appThemeState.update {
            it.copy(theme = theme)
        }
    }

    override suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
        _appThemeState.update {
            it.copy(dynamicColors = dynamicColor)
        }
    }

    override fun setCustomTheme(customTheme: Theme?) {
        _appThemeState.update {
            it.copy(customTheme = customTheme)
        }
    }

    override suspend fun getAppThemeFromDataStore() {
        val appTheme = preferenceDataSource.getData { preferences ->
            val theme = preferences[PreferencesDataKeys.appTheme]
            val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
            appThemeModelMapper.invoke(theme, dynamicColors)
        }.first()
        _appThemeState.update { appTheme }
    }
}