package com.openweather.core.domain.managers

import com.openweather.core.domain.models.session.SessionWithUserModel

interface SessionManager {

    suspend fun createSession(sessionWithUser: SessionWithUserModel)

    suspend fun needsLogin(): Boolean

    suspend fun isSessionValid(): Boolean
}