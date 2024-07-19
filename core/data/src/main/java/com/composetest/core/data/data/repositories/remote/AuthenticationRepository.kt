package com.composetest.core.data.data.repositories.remote

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ): Flow<T>
}