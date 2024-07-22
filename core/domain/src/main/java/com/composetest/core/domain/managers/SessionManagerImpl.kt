package com.composetest.core.domain.managers

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.database.data.repositories.SessionRepository
import com.composetest.core.database.data.repositories.UserRepository
import com.composetest.core.domain.mappers.SessionEntityMapper
import com.composetest.core.domain.mappers.SessionModelMapper
import com.composetest.core.domain.mappers.UserEntityMapper
import com.composetest.core.domain.models.session.SessionWithUserModel
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionModelMapper: SessionModelMapper,
    private val dateTimeProvider: DateTimeProvider,
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
        val currentSession = sessionRepository
            .getCurrentSession(sessionModelMapper::invoke) ?: return false
        val finishDateSession = getFinishDateSession(currentSession.initialDate)
        val nowDateTime = dateTimeProvider.nowDateTime
        val validSession = nowDateTime < finishDateSession
        if (!validSession) {
            sessionRepository.update(sessionEntityMapper(currentSession.id, nowDateTime))
        }
        return validSession
    }

    private fun getFinishDateSession(initialDate: LocalDateTime) =
        initialDate.plusWeeks(SESSION_WEEK_DURATION)

    private companion object {
        const val SESSION_WEEK_DURATION = 2L
    }
}