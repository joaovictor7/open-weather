package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.domain.constants.preferencedata.PreferencesDataKeys
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppThemeRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) : AppThemeRepository {

    override suspend fun setTheme(theme: String) {
        preferenceDataSource.setData(PreferencesDataKeys.appTheme, theme)
    }

    override suspend fun setDynamicColor(dynamicColor: Boolean) {
        preferenceDataSource.setData(PreferencesDataKeys.dynamicColor, dynamicColor)
    }

    override fun <T> getAppTheme(converter: (String?, Boolean?) -> T) =
        preferenceDataSource.getData { preferences ->
            val theme = preferences[PreferencesDataKeys.appTheme]
            val dynamicColors = preferences[PreferencesDataKeys.dynamicColor]
            converter.invoke(theme, dynamicColors)
        }
}