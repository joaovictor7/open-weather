package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.datasources.remote.base.BaseRemoteDataSource
import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.flow

internal class FirebaseAuthDataSourceFakeImpl(context: Context) :
    BaseRemoteDataSource(context),
    FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        safeRemoteCall {}
        val fake = AuthenticationResponse(
            id = "123",
            email = "teste@teste.com",
            name = "Teste"
        )
        emit(fake)
    }
}