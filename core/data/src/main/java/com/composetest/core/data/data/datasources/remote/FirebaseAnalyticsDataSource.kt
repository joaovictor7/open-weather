package com.composetest.core.data.data.datasources.remote

import android.os.Bundle

internal interface FirebaseAnalyticsDataSource{
    suspend fun logEvent(tag: String, params: Bundle)

    suspend fun logNonFatalError(tag: String, params: Bundle, throwable: Throwable)
}