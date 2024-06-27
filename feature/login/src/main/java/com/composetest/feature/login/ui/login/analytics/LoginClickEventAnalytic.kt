package com.composetest.feature.login.ui.login.analytics

import com.composetest.common.analytics.ClickAnalyticEvent

internal class LoginClickEventAnalytic : ClickAnalyticEvent(
    LoginAnalytic(),
    "login_button"
)
