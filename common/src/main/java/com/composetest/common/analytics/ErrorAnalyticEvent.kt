package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen

open class ErrorAnalyticEvent(
    val throwable: Throwable,
    analyticScreen: AnalyticScreen?
) : AnalyticEvent {
    override val tag = "throwable"
    final override val screen = analyticScreen?.screen
    final override val params = mapOf(
        "message" to (throwable.message ?: "No error message")
    )
}
