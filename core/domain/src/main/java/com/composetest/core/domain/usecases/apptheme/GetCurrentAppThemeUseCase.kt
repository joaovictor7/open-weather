package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.domain.converters.AppThemeModelConverter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCurrentAppThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    operator fun invoke() = appThemeRepository.currentAppTheme
}