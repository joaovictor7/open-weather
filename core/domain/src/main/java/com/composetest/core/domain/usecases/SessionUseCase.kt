package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface SessionUseCase {
    suspend fun createSession(userModel: UserModel)
    fun getCurrentUser(): Flow<UserModel>
}