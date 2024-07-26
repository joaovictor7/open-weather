package com.composetest.core.data.managers

import com.composetest.common.providers.NetworkProvider
import com.composetest.common.throwables.NetworkThrowable
import javax.inject.Inject

internal class RemoteCallManagerImpl @Inject constructor(
    private val networkProvider: NetworkProvider
) : RemoteCallManager {

    override suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !networkProvider.internetIsConnected -> throw NetworkThrowable()
        else -> onRemoteCall()
    }
}