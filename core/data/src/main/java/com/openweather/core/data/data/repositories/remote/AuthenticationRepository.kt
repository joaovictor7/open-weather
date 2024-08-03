package com.openweather.core.data.data.repositories.remote

import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.data.network.responses.AuthenticationResponse

interface AuthenticationRepository {
    suspend fun <T> authentication(
        request: AuthenticationRequest,
        mapper: (AuthenticationResponse) -> T
    ): T
}