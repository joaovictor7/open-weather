package com.composetest.core.data.domain.bases

import android.content.Context
import com.composetest.core.data.throwables.RemoteNetworkThrowable
import com.composetest.core.utility.extensions.internetIsConnected

internal open class BaseRemoteDataSource(private val context: Context) {

    protected suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T = when {
        !context.internetIsConnected -> throw RemoteNetworkThrowable()
        else -> onRemoteCall.invoke()
    }
}