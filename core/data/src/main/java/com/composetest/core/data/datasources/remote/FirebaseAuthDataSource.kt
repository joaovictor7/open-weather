package com.composetest.core.data.datasources.remote

import com.composetest.core.data.network.requests.AuthenticationRequest
import com.composetest.core.data.network.responses.AuthenticationResponse
import kotlinx.coroutines.flow.Flow

internal interface FirebaseAuthDataSource {
    fun authentication(request: AuthenticationRequest): Flow<AuthenticationResponse>
}