package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.Analytic
import com.composetest.common.analytics.interfaces.AnalyticEvent

open class OpenScreenAnalyticEvent(
    analytic: Analytic
) : AnalyticEvent, Analytic {
    final override val screen = analytic.screen
    final override val tag = "open_screen"
    final override val params = mapOf(
        "screen" to screen
    )
}