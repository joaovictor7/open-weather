package com.composetest.core.domain.usecases.apptheme

import com.composetest.common.enums.Theme
import com.composetest.core.data.repositories.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SetThemeUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {

    suspend operator fun invoke(theme: Theme) = appThemeRepository.setTheme(theme)
}