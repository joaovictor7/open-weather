package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import com.composetest.core.data.providers.RemoteCallProvider
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteCallProvider: RemoteCallProvider,
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = remoteCallProvider.safeRemoteCall(
        authenticationDataSource.authentication(request)
    ).map(mapper)
}