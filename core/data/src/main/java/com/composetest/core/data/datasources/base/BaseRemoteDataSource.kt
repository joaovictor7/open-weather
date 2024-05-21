package com.composetest.core.data.datasources.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.composetest.core.data.domain.exceptions.RemoteNetworkException

internal open class BaseRemoteDataSource(
    private val context: Context
) {
    suspend fun <T> remoteCall(onRemoteCall: suspend () -> T) = when {
        !checkNetworkConnection() -> throw RemoteNetworkException()
        else -> onRemoteCall.invoke()
    }

    private fun checkNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}