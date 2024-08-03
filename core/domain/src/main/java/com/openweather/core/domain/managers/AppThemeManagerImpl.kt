package com.openweather.core.domain.managers

import com.openweather.common.enums.Theme
import com.openweather.core.data.data.repositories.local.AppThemeRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class AppThemeManagerImpl @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) : AppThemeManager {

    override fun getAppTheme() = appThemeRepository
        .getAppTheme()
        .combine(appThemeRepository.customAppTheme) { appTheme, customTheme ->
            appTheme.copy(customTheme = customTheme)
        }

    override suspend fun setTheme(theme: Theme) = appThemeRepository.setTheme(theme)

    override suspend fun setDynamicColor(dynamicColor: Boolean) =
        appThemeRepository.setDynamicColor(dynamicColor)

    override fun setCustomTheme(customTheme: Theme?) = appThemeRepository.setCustomTheme(customTheme)
}