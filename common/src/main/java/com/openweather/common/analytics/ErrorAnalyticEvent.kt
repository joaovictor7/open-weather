package com.openweather.common.analytics

import com.openweather.common.analytics.interfaces.AnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticScreen

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
