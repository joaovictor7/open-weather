package com.composetest.core.data.providers

import com.composetest.common.providers.NetworkProvider
import com.composetest.common.throwables.NetworkThrowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteCallProviderImpl @Inject constructor(
    private val networkProvider: NetworkProvider
) : RemoteCallProvider {

    override suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !networkProvider.internetIsConnected -> throw NetworkThrowable()
        else -> onRemoteCall.invoke()
    }
}