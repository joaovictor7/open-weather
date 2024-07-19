package com.composetest.core.data.providers

import kotlinx.coroutines.flow.Flow

internal interface RemoteCallProvider {
    fun <T> safeRemoteCall(onRemoteCall: Flow<T>): Flow<T>
}