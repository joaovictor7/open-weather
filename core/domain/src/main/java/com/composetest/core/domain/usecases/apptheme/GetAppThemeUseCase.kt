package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.local.AppThemeRepository
import com.composetest.core.domain.mappers.AppThemeModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository,
    private val appThemeModelMapper: AppThemeModelMapper
) {
    operator fun invoke() = appThemeRepository.getAppTheme { theme, dynamicColor ->
        appThemeModelMapper(
            theme,
            dynamicColor,
            appThemeRepository.currentAppTheme.customTheme
        )
    }
}