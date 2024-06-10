package com.composetest.core.data.datasources.remote

import com.composetest.common.providers.RemoteCallProvider
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.network.responses.UserResponse
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class FirebaseAuthDataSourceFakeImpl(
    private val remoteCallProvider: RemoteCallProvider
) : FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        remoteCallProvider.safeRemoteCall {}
        val fake = AuthenticationResponse(
            token = "43reddcdsfe434323cdf3434",
            authenticationDate = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC),
            user = UserResponse(
                id = "123",
                name = "Teste",
                email = "teste@teste.com"
            )
        )
        emit(fake)
    }
}