package com.composetest.core.data.repositories.remote

import androidx.core.os.bundleOf
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: FirebaseAnalyticsDataSource
) : AnalyticsRepository {

    override fun logEvent(request: AnalyticRequest) {
        val bundle = bundleOf().apply {
            request.params.forEach {
                putString(it.key, it.value.toString())
            }
        }
        analyticsDataSource.logEvent(request.tag, bundle)
    }

    override fun logNonFatalError(request: ErrorAnalyticRequest) {
        val bundle = bundleOf().apply {
            request.params.forEach {
                putString(it.key, it.value.toString())
            }
        }
        analyticsDataSource.logNonFatalError(request.tag, bundle, request.throwable)
    }
}