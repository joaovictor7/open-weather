package com.composetest.core.data.providers

internal interface RemoteCallProvider {
    suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T
}