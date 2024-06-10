package com.composetest.common.providers

import android.content.Context
import com.composetest.common.extensions.internetIsConnected
import com.composetest.common.throwables.RemoteNetworkThrowable
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteCallProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RemoteCallProvider{
    override suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !context.internetIsConnected -> throw RemoteNetworkThrowable()
        else -> onRemoteCall.invoke()
    }
}