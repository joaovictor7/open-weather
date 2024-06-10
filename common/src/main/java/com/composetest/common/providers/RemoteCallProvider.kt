package com.composetest.common.providers

interface RemoteCallProvider {
    suspend fun <T> safeRemoteCall(onRemoteCall: suspend () -> T): T
}