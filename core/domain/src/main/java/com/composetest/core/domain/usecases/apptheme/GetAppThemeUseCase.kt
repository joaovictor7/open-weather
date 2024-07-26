package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.data.repositories.local.AppThemeRepository
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    operator fun invoke() = appThemeRepository
        .getAppTheme()
        .combine(appThemeRepository.customAppTheme) { appTheme, customTheme ->
            appTheme.copy(customTheme = customTheme)
        }
}