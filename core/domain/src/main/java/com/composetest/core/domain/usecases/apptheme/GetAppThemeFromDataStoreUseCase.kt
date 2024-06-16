package com.composetest.core.domain.usecases.apptheme

import com.composetest.core.data.repositories.local.AppThemeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppThemeFromDataStoreUseCase @Inject constructor(
    private val appThemeRepository: AppThemeRepository
) {
    suspend operator fun invoke() {
        appThemeRepository.getAppThemeFromDataStore()
    }
}