package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.remote.requests.LoginRequest
import com.composetest.core.data.domain.remote.responses.UserResponse
import kotlinx.coroutines.flow.Flow

internal interface FirebaseAuthDataSource {
    fun login(login: LoginRequest): Flow<UserResponse>
}