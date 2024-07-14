package com.composetest.core.data.datasources.remote

import android.os.Bundle

internal interface FirebaseAnalyticsDataSource{
    fun logEvent(tag: String, params: Bundle)

    fun logNonFatalError(tag: String, params: Bundle, throwable: Throwable)
}