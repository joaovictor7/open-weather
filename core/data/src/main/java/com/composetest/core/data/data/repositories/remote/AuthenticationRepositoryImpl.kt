package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.providers.RemoteCallProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class AuthenticationRepositoryImpl(
    private val remoteCallProvider: RemoteCallProvider,
    private val authenticationDataSource: AuthenticationDataSourceImpl
) : AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = flow {
        val response = remoteCallProvider.safeRemoteCall {
            authenticationDataSource.authentication(request)
        }
        emit(response)
    }.map(mapper::invoke)

    override suspend fun <T> authentication1(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ): T {
        val response = remoteCallProvider.safeRemoteCall {
            authenticationDataSource.authentication(request)
        }
        return mapper(response)
    }
}