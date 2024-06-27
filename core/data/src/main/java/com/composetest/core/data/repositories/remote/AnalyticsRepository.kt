package com.composetest.core.data.repositories.remote

import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest

interface AnalyticsRepository {
    fun logEvent(request: AnalyticRequest)
    fun logNonFatalError(request: ErrorAnalyticRequest)
}