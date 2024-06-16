package com.composetest.core.domain.usecases.apptheme

import com.composetest.common.enums.Theme
import com.composetest.core.data.repositories.local.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {

    suspend operator fun invoke(theme: Theme) = appThemeRepository.setTheme(theme)

    suspend operator fun invoke(dynamicColor: Boolean) =
        appThemeRepository.setDynamicColor(dynamicColor)

    operator fun invoke(customTheme: Theme?) = appThemeRepository.setCustomTheme(customTheme)
}