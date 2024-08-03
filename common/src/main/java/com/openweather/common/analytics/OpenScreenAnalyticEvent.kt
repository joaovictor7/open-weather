package com.openweather.common.analytics

import com.openweather.common.analytics.interfaces.AnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticScreen

open class OpenScreenAnalyticEvent(
    analyticScreen: AnalyticScreen
) : AnalyticEvent {
    final override val tag = "open_screen"
    final override val screen = analyticScreen.screen
    final override val params = emptyMap<String, Any>()
}