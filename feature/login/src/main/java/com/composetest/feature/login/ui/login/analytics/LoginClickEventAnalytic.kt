package com.composetest.feature.login.ui.login.analytics

import com.composetest.common.analytics.ClickAnalyticEvent

internal object LoginClickEventAnalytic : ClickAnalyticEvent(
    clickEvent = "login_button",
    analyticScreen = LoginScreenAnalytic
)
