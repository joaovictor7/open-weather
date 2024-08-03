package com.openweather.core.data.data.repositories.remote

import android.os.Bundle
import androidx.core.os.bundleOf
import com.openweather.common.analytics.ErrorAnalyticEvent
import com.openweather.common.analytics.interfaces.AnalyticEvent
import com.openweather.common.providers.BuildConfigProvider
import com.openweather.common.providers.DateTimeProvider
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import javax.inject.Inject

internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: FirebaseAnalyticsDataSourceImpl,
    private val dateTimeProvider: DateTimeProvider,
    private val buildConfigProvider: BuildConfigProvider
) : AnalyticsRepository {

    override suspend fun logEvent(event: AnalyticEvent) {
        val bundle = createBundle(event)
        analyticsDataSource.logEvent(event.tag, bundle)
    }

    override suspend fun logNonFatalError(event: ErrorAnalyticEvent) {
        val bundle = createBundle(event)
        analyticsDataSource.logNonFatalError(event.tag, event.throwable, bundle)
    }

    private fun createBundle(event: AnalyticEvent): Bundle {
        return bundleOf(
            DATE_TIME to dateTimeProvider.nowDateTime.toString(),
            APP_VERSION to buildConfigProvider.get.versionName,
            ANDROID_SDK_VERSION to buildConfigProvider.get.androidSdkVersion.toString(),
            *event.params.map { it.key to it.value.toString() }.toTypedArray()
        ).apply {
            event.screen?.let { putString(SCREEN, it) }
        }
    }

    private companion object {
        const val DATE_TIME = "date_time"
        const val APP_VERSION = "app_version"
        const val ANDROID_SDK_VERSION = "android_sdk_version"
        const val SCREEN = "screen"
    }
}