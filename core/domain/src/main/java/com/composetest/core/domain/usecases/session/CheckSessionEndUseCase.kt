package com.composetest.core.domain.usecases.session

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.repositories.local.SessionRepository
import com.composetest.core.domain.mappers.SessionEntityMapper
import com.composetest.core.domain.mappers.SessionModelMapper
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckSessionEndUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val sessionModelMapper: SessionModelMapper,
    private val sessionEntityMapper: SessionEntityMapper,
    private val dateTimeProvider: DateTimeProvider
) {

    suspend operator fun invoke(): Boolean {
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