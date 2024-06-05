package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.SessionModel
import com.composetest.core.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface SessionUseCase {
    suspend fun createSession(session: SessionModel)
    fun getCurrentUser(): Flow<UserModel>
}