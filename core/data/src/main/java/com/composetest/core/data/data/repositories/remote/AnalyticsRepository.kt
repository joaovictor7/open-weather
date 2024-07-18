package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest

interface AnalyticsRepository {
    suspend fun logEvent(request: AnalyticRequest)
    suspend fun logNonFatalError(request: ErrorAnalyticRequest)
}