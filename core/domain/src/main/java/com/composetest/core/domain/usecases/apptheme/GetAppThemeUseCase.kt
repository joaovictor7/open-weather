package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.domain.converters.AppThemeModelConverter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository,
    private val appThemeModelConverter: AppThemeModelConverter
) {
    operator fun invoke() = appThemeRepository.getAppTheme { theme, dynamicColor ->
        appThemeModelConverter(
            theme,
            dynamicColor,
            appThemeRepository.currentAppTheme.customTheme
        )
    }
}