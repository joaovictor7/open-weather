package com.composetest.common.analytics

open class BackgroundErrorAnalyticEvent(
    throwable: Throwable
) : ErrorAnalyticEvent(throwable, null) {
    final override val tag = "background_throwable"
}
