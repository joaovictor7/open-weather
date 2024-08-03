package com.openweather.core.domain.managers

import com.openweather.core.data.data.repositories.local.WorkManagerRepository
import com.openweather.core.data.workmanagers.workes.SessionWorker
import com.openweather.core.data.data.repositories.local.SessionRepository
import com.openweather.core.data.data.repositories.local.UserRepository
import com.openweather.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import com.openweather.core.domain.mappers.SessionEntityMapper
import com.openweather.core.domain.mappers.SessionModelMapper
import com.openweather.core.domain.mappers.UserEntityMapper
import com.openweather.core.domain.models.session.SessionWithUserModel
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

internal class SessionManagerImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionModelMapper: SessionModelMapper,
    private val sessionEntityMapper: SessionEntityMapper,
    private val userEntityMapper: UserEntityMapper,
    private val workManagerRepository: WorkManagerRepository
) : SessionManager {

    override suspend fun createSession(sessionWithUser: SessionWithUserModel) {
        userRepository.insert(userEntityMapper(sessionWithUser.user))
        sessionRepository.insert(sessionEntityMapper(sessionWithUser))
        createSessionSchedule(sessionWithUser.startDate, sessionWithUser.endDate)
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

    private fun createSessionSchedule(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ) {
        val duration = Duration.between(startDate, endDate)
        workManagerRepository.createOneTimeWork(SessionWorker.Builder(duration))
    }
}