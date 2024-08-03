package com.openweather.common.analytics

import com.openweather.common.analytics.interfaces.AnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticScreen

open class ClickAnalyticEvent(
    clickEvent: String,
    analyticScreen: AnalyticScreen
) : AnalyticEvent {
    final override val screen = analyticScreen.screen
    final override val tag = "click"
    final override val params = mapOf(
        "clicked" to clickEvent
    )
}