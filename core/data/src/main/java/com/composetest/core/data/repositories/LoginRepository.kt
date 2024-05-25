package com.composetest.core.data.repositories

import com.composetest.core.data.domain.remote.requests.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(login: LoginRequest): Flow<Boolean>
}