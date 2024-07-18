package com.composetest.core.data.data.repositories.remote

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.UserResponse
import com.composetest.core.data.providers.RemoteCallProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlin.time.Duration.Companion.seconds

internal class AuthenticationRepositoryFakeImpl(
    private val remoteCallProvider: RemoteCallProvider,
    private val dateTimeProvider: DateTimeProvider
) : AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = flow {
        val response = remoteCallProvider.safeRemoteCall {
            AuthenticationResponse(
                token = "43reddcdsfe434323cdf3434",
                authenticationDate = dateTimeProvider.nowDateTime.toString(),
                user = UserResponse(
                    id = "123",
                    name = "Teste",
                    email = "teste@teste.com"
                )
            )
        }
        delay(2.seconds)
        emit(response)
    }.map(mapper::invoke)

    override suspend fun <T> authentication1(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ): T {
        TODO("Not yet implemented")
    }
}