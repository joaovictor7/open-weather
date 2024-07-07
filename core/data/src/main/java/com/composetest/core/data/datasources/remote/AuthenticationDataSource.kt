package com.composetest.core.data.datasources.remote

import com.composetest.core.data.network.requests.AuthenticationRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationDataSource @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun authentication(
        authenticationRequest: AuthenticationRequest
    ) = httpClient.post("authenticate") {
        setBody(authenticationRequest)
    }
}