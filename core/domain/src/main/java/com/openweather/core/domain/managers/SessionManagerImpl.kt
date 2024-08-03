package com.openweather.core.domain.managers

import com.openweather.core.data.data.repositories.local.SessionRepository
import com.openweather.core.data.data.repositories.local.UserRepository
import com.openweather.core.domain.mappers.SessionEntityMapper
import com.openweather.core.domain.mappers.SessionModelMapper
import com.openweather.core.domain.mappers.UserEntityMapper
import com.openweather.core.domain.models.session.SessionWithUserModel
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionModelMapper: SessionModelMapper,
    private val sessionEntityMapper: SessionEntityMapper,
    private val userEntityMapper: UserEntityMapper
) : SessionManager {

    override suspend fun createSession(sessionWithUser: SessionWithUserModel) {
        userRepository.insert(userEntityMapper(sessionWithUser.user))
        sessionRepository.insert(sessionEntityMapper(sessionWithUser))
    }

    override suspend fun needsLogin() = sessionRepository
        .getCurrentSession(sessionModelMapper::invoke).let { currentSession ->
            currentSession == null
        }

    override suspend fun isSessionValid(): Boolean {
        val currentSession = sessionRepository.getCurrentSession(
            sessionModelMapper::invoke
        ) ?: return false
        return !currentSession.isFinished
    }
}