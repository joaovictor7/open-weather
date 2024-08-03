package com.openweather.feature.login.ui.login.analytics

import com.openweather.common.analytics.ClickAnalyticEvent

internal object LoginClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = LoginScreenAnalytic
)
