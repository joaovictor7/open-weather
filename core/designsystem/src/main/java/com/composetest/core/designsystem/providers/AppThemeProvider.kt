package com.composetest.core.designsystem.providers

import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.designsystem.domain.converters.AppThemeModelConverter
import com.composetest.core.designsystem.domain.emuns.Theme
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
    private val appThemeRepository: AppThemeRepository,
    private val appThemeModelConverter: AppThemeModelConverter
) {

    private val _appThemeState = MutableStateFlow(AppThemeModel())
    val appThemeState = _appThemeState.asStateFlow()

    val get get() = appThemeState.value

    init {
        getAppTheme()
    }

    suspend fun setAppTheme(theme: Theme) {
        appThemeRepository.setTheme(theme.name)
    }

    suspend fun setDynamicColors(dynamicColors: Boolean) {
        appThemeRepository.setDynamicColor(dynamicColors)
    }

    fun setCustomTheme(customTheme: Theme?) =
        _appThemeState.update { it.copy(customTheme = customTheme) }

    private fun getAppTheme() {
        CoroutineScope(Dispatchers.IO).launch {
            appThemeRepository.getAppTheme { theme, dynamicColor ->
                appThemeModelConverter.convertTo(
                    theme,
                    dynamicColor,
                    appThemeState.value.customTheme
                )
            }.collect { appThemeModel ->
                _appThemeState.update { appThemeModel }
            }
        }
    }
}