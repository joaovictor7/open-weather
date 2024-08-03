package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.data.datasources.remote.AuthenticationDataSource
import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.data.network.responses.AuthenticationResponse
import javax.inject.Inject

internal class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ) = mapper(authenticationDataSource.authentication(request))
}