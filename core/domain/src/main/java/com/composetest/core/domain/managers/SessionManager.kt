package com.composetest.core.domain.managers

import com.composetest.core.domain.models.session.SessionWithUserModel

interface SessionManager {

    suspend fun createSession(sessionWithUser: SessionWithUserModel)

    suspend fun finishSession(sessionWithUser: SessionWithUserModel)

    suspend fun needsLogin(): Boolean

    suspend fun isSessionValid(): Boolean
}