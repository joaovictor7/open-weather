package com.composetest.core.designsystem.providers

import com.composetest.core.utility.extensions.orFalse
import com.composetest.core.data.domain.constants.preferencedata.PreferencesDataKeys
import com.composetest.core.data.repositories.PreferencesDataRepository
import com.composetest.core.designsystem.domain.emuns.AppTheme
import com.composetest.core.designsystem.domain.models.AppThemeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppThemeProvider @Inject constructor(
    private val preferencesDataRepository: PreferencesDataRepository
) {

    private val _appThemeState = MutableStateFlow(AppThemeModel())
    val appThemeState = _appThemeState.asStateFlow()

    val get get() = appThemeState.value

    init {
        getAppTheme()
    }

    suspend fun setAppTheme(theme: AppTheme) {
        preferencesDataRepository.setData(PreferencesDataKeys.appTheme, theme.name)
    }

    suspend fun setDynamicColors(dynamicColors: Boolean) {
        preferencesDataRepository.setData(PreferencesDataKeys.dynamicColor, dynamicColors)
    }

    fun setCustomTheme(customTheme: AppTheme?) =
        _appThemeState.update { it.copy(customTheme = customTheme) }

    private fun getAppTheme() {
        CoroutineScope(Dispatchers.IO).launch {
            preferencesDataRepository.getData { preferences ->
                appThemeState.value.copy(
                    theme = AppTheme.getAppTheme(preferences[PreferencesDataKeys.appTheme]),
                    dynamicColors = preferences[PreferencesDataKeys.dynamicColor].orFalse
                )
            }.collect { appThemeModel ->
                _appThemeState.update { appThemeModel }
            }
        }
    }
}