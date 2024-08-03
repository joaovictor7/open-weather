package com.openweather.core.data.data.datasources.remote

import android.os.Bundle

internal interface FirebaseAnalyticsDataSource {
    fun logEvent(tag: String, params: Bundle)

    fun logNonFatalError(tag: String, throwable: Throwable, params: Bundle)
}