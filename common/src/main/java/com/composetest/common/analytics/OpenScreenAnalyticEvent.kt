package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen

open class OpenScreenAnalyticEvent(
    analyticScreen: AnalyticScreen
) : AnalyticEvent {
    final override val tag = "open_screen"
    final override val screen = analyticScreen.screen
    final override val params = emptyMap<String, Any>()
}