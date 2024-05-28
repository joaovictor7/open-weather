package com.composetest.core.data.repositories

import com.composetest.core.data.domain.models.network.requests.LoginRequest
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(login: LoginRequest): Flow<Boolean>
}