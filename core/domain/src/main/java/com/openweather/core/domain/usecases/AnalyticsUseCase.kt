package com.openweather.core.domain.usecases

import com.openweather.common.analytics.ErrorAnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticEvent
import com.openweather.core.data.data.repositories.remote.AnalyticsRepository
import javax.inject.Inject

class AnalyticsUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) {

    suspend operator fun invoke(event: AnalyticEvent) {
        analyticsRepository.logEvent(event)
    }

    suspend operator fun invoke(event: ErrorAnalyticEvent) {
        analyticsRepository.logNonFatalError(event)
    }
}