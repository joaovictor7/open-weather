package com.composetest.core.data.datasources.remote

import android.content.Context
import com.composetest.core.data.domain.bases.BaseRemoteDataSource
import com.composetest.core.data.domain.models.network.requests.AuthenticationRequest
import com.composetest.core.data.domain.models.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.flow

internal class FirebaseAuthDataSourceFakeImpl(context: Context) :
    BaseRemoteDataSource(context),
    FirebaseAuthDataSource {

    override fun authentication(request: AuthenticationRequest) = flow {
        safeRemoteCall {}
        emit(AuthenticationResponse())
    }
}