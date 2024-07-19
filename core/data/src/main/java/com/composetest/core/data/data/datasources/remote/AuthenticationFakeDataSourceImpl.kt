package com.composetest.core.data.data.datasources.remote

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.UserResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

internal class AuthenticationFakeDataSourceImpl(
    private val dateTimeProvider: DateTimeProvider,
    private val ioDispatcher: CoroutineDispatcher
) : AuthenticationDataSource {

    override fun authentication(authenticationRequest: AuthenticationRequest) = flow {
        val response = AuthenticationResponse(
            token = "43reddcdsfe434323cdf3434",
            authenticationDate = dateTimeProvider.nowDateTime.toString(),
            user = UserResponse(
                id = "123",
                name = "Teste",
                email = "teste@teste.com"
            )
        )
        delay(2000)
        emit(response)
    }.flowOn(ioDispatcher)
}