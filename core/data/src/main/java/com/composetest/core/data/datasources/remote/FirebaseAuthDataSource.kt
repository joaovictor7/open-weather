package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.models.network.requests.LoginRequest
import com.composetest.core.data.domain.models.network.responses.UserResponse
import kotlinx.coroutines.flow.Flow

internal interface FirebaseAuthDataSource {
    fun login(login: LoginRequest): Flow<UserResponse>
}