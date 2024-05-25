package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.models.requests.LoginRequest
import com.composetest.core.data.domain.models.responses.LoginResponse
import kotlinx.coroutines.flow.Flow

internal interface FirebaseAuthDataSource {
    fun login(login: LoginRequest): Flow<LoginResponse>
}