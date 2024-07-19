package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.extensions.post
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class AuthenticationDataSourceImpl(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationDataSource {

    override suspend fun authentication(authenticationRequest: AuthenticationRequest) =
        withContext(ioDispatcher) {
             httpClient.post<AuthenticationResponse>("authenticate") {
                setBody(authenticationRequest)
            }
        }
}