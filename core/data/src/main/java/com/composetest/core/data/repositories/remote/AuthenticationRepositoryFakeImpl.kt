package com.composetest.core.data.repositories.remote

import com.composetest.common.providers.RemoteCallProvider
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.UserResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.time.Duration.Companion.seconds

internal class AuthenticationRepositoryFakeImpl(
    private val remoteCallProvider: RemoteCallProvider
): AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = flow {
        val response = remoteCallProvider.safeRemoteCall {
            AuthenticationResponse(
                token = "43reddcdsfe434323cdf3434",
                authenticationDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
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
}