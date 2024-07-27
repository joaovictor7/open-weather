package com.composetest.core.data.data.datasources.remote

import android.os.Bundle
import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FirebaseAnalyticsDataSourceImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val firebaseCrashlytics: FirebaseCrashlytics,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : FirebaseAnalyticsDataSource {

    override suspend fun logEvent(tag: String, params: Bundle) = withContext(ioDispatcher) {
        firebaseAnalytics.logEvent(tag, params)
    }

    override suspend fun logNonFatalError(tag: String, throwable: Throwable, params: Bundle) =
        withContext(ioDispatcher) {
            firebaseAnalytics.logEvent(tag, params)
            firebaseCrashlytics.recordException(throwable)
        }
}