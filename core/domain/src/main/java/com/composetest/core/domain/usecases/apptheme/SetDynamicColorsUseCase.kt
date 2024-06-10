package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetDynamicColorsUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    suspend operator fun invoke(dynamicColor: Boolean) =
        appThemeRepository.setDynamicColor(dynamicColor)
}