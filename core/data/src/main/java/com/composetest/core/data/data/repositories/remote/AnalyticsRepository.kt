package com.composetest.core.data.data.repositories.remote

import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticEvent

interface AnalyticsRepository {
    suspend fun logEvent(event: AnalyticEvent)
    suspend fun logNonFatalError(event: ErrorAnalyticEvent)
}