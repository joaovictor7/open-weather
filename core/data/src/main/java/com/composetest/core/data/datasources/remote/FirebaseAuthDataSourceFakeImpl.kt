package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.remote.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import com.composetest.core.data.domain.models.network.responses.UserResponse
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.ZoneOffset

internal class FirebaseAuthDataSourceFakeImpl(context: Context) :
    BaseRemoteDataSource(context),
    FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        safeRemoteCall {}
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