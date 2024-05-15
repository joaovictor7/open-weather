package com.composetest.core.data.datasources.remote

import com.composetest.core.data.domain.requests.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    fun login(login: LoginRequest): Flow<Boolean>
}