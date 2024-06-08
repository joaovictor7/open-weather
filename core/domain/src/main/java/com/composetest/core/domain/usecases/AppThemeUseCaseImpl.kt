package com.composetest.core.domain.usecases

import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.domain.converters.AppThemeModelConverter
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.enums.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppThemeUseCaseImpl @Inject constructor(
    private val appThemeRepository: AppThemeRepository,
    private val appThemeModelConverter: AppThemeModelConverter
) : AppThemeUseCase {

    private val _appThemeState = MutableStateFlow(AppThemeModel())
    override val appThemeState = _appThemeState.asStateFlow()

    override val currentAppTheme get() = appThemeState.value

    init {
        getAppTheme()
    }

    override suspend fun setAppTheme(theme: Theme) {
        appThemeRepository.setTheme(theme.name)
    }

    override suspend fun setDynamicColors(dynamicColors: Boolean) {
        appThemeRepository.setDynamicColor(dynamicColors)
    }

    override fun setCustomTheme(customTheme: Theme?) =
        _appThemeState.update { it.copy(customTheme = customTheme) }

    private fun getAppTheme() {
        CoroutineScope(Dispatchers.IO).launch {
            appThemeRepository.getAppTheme { theme, dynamicColor ->
                appThemeModelConverter(
                    theme,
                    dynamicColor,
                    currentAppTheme.customTheme
                )
            }.collect { appThemeModel ->
                _appThemeState.update { appThemeModel }
            }
        }
    }
}