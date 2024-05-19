package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.models.requests.LoginRequest
import kotlinx.coroutines.flow.Flow

internal interface LoginDataSource {
    fun login(login: LoginRequest): Flow<Boolean>
}