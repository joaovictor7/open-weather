package com.composetest.common.analytics

open class WorkerErrorAnalyticEvent(
    throwable: Throwable
) : ErrorAnalyticEvent(throwable, null) {
    final override val tag = "worker_throwable"
}
