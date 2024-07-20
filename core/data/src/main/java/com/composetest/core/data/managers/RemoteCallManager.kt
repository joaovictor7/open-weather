package com.composetest.core.data.managers

internal interface RemoteCallManager {
    suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T
}