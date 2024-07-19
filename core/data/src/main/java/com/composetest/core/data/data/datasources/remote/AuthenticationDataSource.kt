package com.composetest.core.data.data.datasources.remote

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.Flow

internal interface AuthenticationDataSource {

    fun authentication(
        authenticationRequest: AuthenticationRequest
    ): Flow<AuthenticationResponse>
}