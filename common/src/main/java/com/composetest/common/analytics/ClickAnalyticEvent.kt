package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.Analytic
import com.composetest.common.analytics.interfaces.AnalyticEvent

open class ClickAnalyticEvent(
    analytic: Analytic,
    clickEvent: String
) : AnalyticEvent, Analytic {
    final override val screen = analytic.screen
    final override val tag = "click"
    final override val params = mapOf(
        "clicked" to clickEvent
    )
}