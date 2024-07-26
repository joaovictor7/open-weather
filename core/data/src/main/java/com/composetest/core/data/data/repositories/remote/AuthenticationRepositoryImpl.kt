package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.managers.RemoteCallManager
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteCallManager: RemoteCallManager,
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = remoteCallManager.safeRemoteCall {
        mapper(authenticationDataSource.authentication(request))
    }
}