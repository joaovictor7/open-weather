package com.composetest.core.domain.usecases

import com.composetest.core.data.repositories.local.SessionRepository
import com.composetest.core.data.repositories.local.UserRepository
import com.composetest.core.data.repositories.local.WorkManagerRepository
import com.composetest.core.data.workmanagers.workes.SessionWorker
import com.composetest.core.domain.mappers.SessionEntityMapper
import com.composetest.core.domain.mappers.UserEntityMapper
import com.composetest.core.domain.models.SessionWithUserModel
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

@Singleton
class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val workManagerRepository: WorkManagerRepository,
    private val sessionEntityMapper: SessionEntityMapper,
    private val userEntityMapper: UserEntityMapper
) {

    suspend operator fun invoke(sessionWithUser: SessionWithUserModel) {
        val finishSession = getDateForFinishSession(sessionWithUser.initialDate)
        userRepository.insert(userEntityMapper(sessionWithUser.user))
        sessionRepository.insert(sessionEntityMapper(sessionWithUser))
        workManagerRepository.createOneTimeWork(SessionWorker.Builder(finishSession))
    }

    private fun getDateForFinishSession(initialDate: LocalDateTime): Duration {
        val maxSessionDuration = initialDate.plusWeeks(SESSION_WEEK_DURATION)
        return 3.seconds.toJavaDuration()
        return Duration.between(initialDate, maxSessionDuration)
    }

    private companion object {
        const val SESSION_WEEK_DURATION = 2L
    }
}