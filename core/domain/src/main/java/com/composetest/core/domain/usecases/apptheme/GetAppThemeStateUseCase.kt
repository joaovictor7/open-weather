package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.data.repositories.local.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppThemeStateUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    operator fun invoke() = appThemeRepository.appThemeState
}