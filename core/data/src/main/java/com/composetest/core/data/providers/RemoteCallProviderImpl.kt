package com.composetest.core.data.providers

import com.composetest.common.providers.NetworkProvider
import com.composetest.common.throwables.NetworkThrowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteCallProviderImpl @Inject constructor(
    private val networkProvider: NetworkProvider
) : RemoteCallProvider {

    override fun <T> safeRemoteCall(onRemoteCall: Flow<T>): Flow<T> = onRemoteCall.onEach {
        when {
            !networkProvider.internetIsConnected -> throw NetworkThrowable()
        }
    }
}