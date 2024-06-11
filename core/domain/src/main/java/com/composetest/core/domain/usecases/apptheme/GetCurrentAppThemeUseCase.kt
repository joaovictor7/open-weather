package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.local.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    operator fun invoke() = appThemeRepository.currentAppTheme
}