package com.openweather.core.data.data.datasources.remote

import com.openweather.core.data.network.requests.AuthenticationRequest
import com.openweather.core.data.network.responses.AuthenticationResponse

internal interface AuthenticationDataSource {

    suspend fun authentication(
        authenticationRequest: AuthenticationRequest
    ): AuthenticationResponse
}