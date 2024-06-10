package com.composetest.core.data.repositories

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun <T> authentication(
        request: AuthenticationRequest,
        converter: (AuthenticationResponse) -> T
    ): Flow<T>
}