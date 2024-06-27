package com.composetest.core.data.datasources.remote

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseAnalyticsDataSource @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val firebaseCrashlytics: FirebaseCrashlytics
) {
    fun logEvent(tag: String, params: Bundle) {
        firebaseAnalytics.logEvent(tag, params)
    }

    fun logNonFatalError(tag: String, params: Bundle, throwable: Throwable) {
        firebaseAnalytics.logEvent(tag, params)
        firebaseCrashlytics.recordException(throwable)
    }
}