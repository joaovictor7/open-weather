package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.extensions.post
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class AuthenticationDataSourceImpl(
    private val httpClient: HttpClient,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationDataSource {

    override fun authentication(authenticationRequest: AuthenticationRequest) = flow {
        val response = httpClient.post<AuthenticationResponse>("authenticate") {
            setBody(authenticationRequest)
        }
        emit(response)
    }.flowOn(ioDispatcher)
}