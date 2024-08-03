package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.extensions.post
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody

internal class AuthenticationDataSourceImpl(
    private val httpClient: HttpClient,
    private val safeRemoteCallManager: RemoteCallManager
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationRequest: AuthenticationRequest) =
        safeRemoteCallManager.safeRemoteCall {
            httpClient.post<AuthenticationResponse>("authenticate") {
                setBody(authenticationRequest)
            }
        }
}