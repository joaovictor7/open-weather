package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.SessionWithUserModel

interface SessionUseCase {
    suspend fun createSession(sessionWithUser: SessionWithUserModel)
}