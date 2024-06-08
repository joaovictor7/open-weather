package com.composetest.core.domain.usecases

import com.composetest.core.data.providers.WorkManagerProvider
import com.composetest.core.data.repositories.SessionRepository
import com.composetest.core.data.repositories.UserRepository
import com.composetest.core.data.workmanagers.SessionWorkManager
import com.composetest.core.domain.converters.SessionEntityConverter
import com.composetest.core.domain.converters.UserEntityConverter
import com.composetest.core.domain.models.SessionWithUserModel
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionEntityConverter: SessionEntityConverter,
    private val userEntityConverter: UserEntityConverter,
    private val workManagerProvider: WorkManagerProvider
) : SessionUseCase {

    override suspend fun createSession(sessionWithUser: SessionWithUserModel) {
        val finishSession = getDateForFinishSession(sessionWithUser.initialDate)
        userRepository.insert(userEntityConverter(sessionWithUser.user))
        sessionRepository.insert(sessionEntityConverter(sessionWithUser))
        workManagerProvider.createOneTimeWork(SessionWorkManager.Builder(finishSession))
    }

    private fun getDateForFinishSession(initialDate: LocalDateTime): Duration {
        val maxSessionDuration = initialDate.plusWeeks(SESSION_WEEK_DURATION)
        return Duration.between(initialDate, maxSessionDuration)
    }

    private companion object {
        const val SESSION_WEEK_DURATION = 2L
    }
}