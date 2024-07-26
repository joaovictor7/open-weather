package com.composetest.core.data.data.repositories.remote

import androidx.core.os.bundleOf
import com.composetest.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest
import javax.inject.Inject

internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: FirebaseAnalyticsDataSourceImpl
) : AnalyticsRepository {

    override suspend fun logEvent(request: AnalyticRequest) {
        val bundle = createBundle(request.params)
        analyticsDataSource.logEvent(request.tag, bundle)
    }

    override suspend fun logNonFatalError(request: ErrorAnalyticRequest) {
        val bundle = createBundle(request.params)
        analyticsDataSource.logNonFatalError(request.tag, bundle, request.throwable)
    }

    private fun createBundle(params: Map<String, *>) = bundleOf().apply {
        params.forEach {
            putString(it.key, it.value.toString())
        }
    }
}