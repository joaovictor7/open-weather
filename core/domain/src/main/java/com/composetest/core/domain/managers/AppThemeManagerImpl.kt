package com.composetest.core.domain.managers

import com.composetest.common.enums.Theme
import com.composetest.core.data.data.repositories.local.AppThemeRepository
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