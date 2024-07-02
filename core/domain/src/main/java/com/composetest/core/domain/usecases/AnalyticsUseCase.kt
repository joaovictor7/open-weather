package com.composetest.core.domain.usecases

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.network.requests.AnalyticRequest
import com.composetest.core.data.network.requests.ErrorAnalyticRequest
import com.composetest.core.data.repositories.local.UserRepository
import com.composetest.core.data.repositories.remote.AnalyticsRepository
import com.composetest.core.domain.mappers.UserModelMapper
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnalyticsUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val analyticsRepository: AnalyticsRepository,
    private val userModelMapper: UserModelMapper,
    private val dateTimeProvider: DateTimeProvider,
    private val buildConfigProvider: BuildConfigProvider
) {

    suspend operator fun invoke(event: AnalyticEvent) {
        val request = AnalyticRequest(event.tag, event.params.addDefaultParams())
        analyticsRepository.logEvent(request)
    }

    suspend operator fun invoke(event: ErrorAnalyticEvent) {
        val request = ErrorAnalyticRequest(
            event.tag,
            event.params.addDefaultParams(),
            event.throwable
        )
        analyticsRepository.logNonFatalError(request)
    }

    private suspend fun Map<String, *>.addDefaultParams(): Map<String, *> {
        val user = userRepository.getCurrentUser(userModelMapper::invoke).first()
        return mapOf(
            USER_ID to user?.id.orEmpty(),
            LOGGED_SESSION to (user != null).toString(),
            DATE_TIME to dateTimeProvider.nowDateTime.toString(),
            APP_VERSION to buildConfigProvider.get.versionName,
            ANDROID_SDK_VERSION to buildConfigProvider.get.androidSdkVersion,
        ).plus(this)
    }

    private companion object {
        const val USER_ID = "user_id"
        const val LOGGED_SESSION = "logged_session"
        const val DATE_TIME = "date_time"
        const val APP_VERSION = "app_version"
        const val ANDROID_SDK_VERSION = "android_sdk_version"
    }
}