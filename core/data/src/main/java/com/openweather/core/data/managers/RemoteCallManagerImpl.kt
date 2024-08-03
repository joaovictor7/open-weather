package com.openweather.core.data.managers

import com.openweather.common.di.qualifiers.Dispatcher
import com.openweather.common.enums.Dispatchers
import com.openweather.common.providers.BuildConfigProvider
import com.openweather.common.providers.NetworkProvider
import com.openweather.common.throwables.NetworkThrowable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class RemoteCallManagerImpl @Inject constructor(
    private val networkProvider: NetworkProvider,
    private val buildConfigProvider: BuildConfigProvider,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : RemoteCallManager {

    override suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !networkProvider.internetIsConnected -> throw NetworkThrowable()
        else -> call(onRemoteCall)
    }

    private suspend fun <T> call(onRemoteCall: suspend () -> T) = withContext(ioDispatcher) {
        if (buildConfigProvider.get.isDebug) delay(FAKE_CALL_DELAY)
        onRemoteCall()
    }

    private companion object {
        const val FAKE_CALL_DELAY = 2000L
    }
}