package com.composetest.core.data.repositories.remote

import androidx.core.os.bundleOf
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: FirebaseAnalyticsDataSourceImpl
) : AnalyticsRepository {

    override fun logEvent(request: AnalyticRequest) {
        val bundle = createBundle(request.params)
        analyticsDataSource.logEvent(request.tag, bundle)
    }

    override fun logNonFatalError(request: ErrorAnalyticRequest) {
        val bundle = createBundle(request.params)
        analyticsDataSource.logNonFatalError(request.tag, bundle, request.throwable)
    }

    private fun createBundle(params: Map<String, *>) = bundleOf().apply {
        params.forEach {
            putString(it.key, it.value.toString())
        }
    }
}