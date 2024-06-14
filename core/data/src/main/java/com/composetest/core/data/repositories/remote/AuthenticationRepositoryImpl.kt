package com.composetest.core.data.repositories.remote

import com.composetest.common.providers.RemoteCallProvider
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class AuthenticationRepositoryImpl(
    private val remoteCallProvider: RemoteCallProvider,
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = flow {
        val response = remoteCallProvider.safeRemoteCall {
            firebaseAuthDataSource.authentication(request)
        }
        emit(response)
    }.map(mapper::invoke)
}