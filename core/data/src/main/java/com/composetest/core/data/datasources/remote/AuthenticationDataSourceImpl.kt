package com.composetest.core.data.datasources.remote

import com.composetest.core.data.extensions.post
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient
) : AuthenticationDataSource {

    override suspend fun authentication(
        authenticationRequest: AuthenticationRequest
    ) = httpClient.post<AuthenticationResponse>("authenticate") {
        setBody(authenticationRequest)
    }
}