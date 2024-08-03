package com.openweather.core.data.data.repositories.remote

import com.openweather.common.analytics.ErrorAnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticEvent

interface AnalyticsRepository {
    suspend fun logEvent(event: AnalyticEvent)
    suspend fun logNonFatalError(event: ErrorAnalyticEvent)
}