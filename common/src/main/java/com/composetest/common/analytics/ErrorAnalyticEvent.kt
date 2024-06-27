package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.Analytic
import com.composetest.common.analytics.interfaces.AnalyticEvent

open class ErrorAnalyticEvent(
    val throwable: Throwable,
    analytic: Analytic
) : AnalyticEvent, Analytic {
    final override val screen: String = analytic.screen
    final override val tag = "throwable"
    final override val params = mapOf(
        "message" to (throwable.message ?: "No error message")
    )
}
