package com.composetest.core.domain.usecases

import com.composetest.core.domain.models.SessionModel
import com.composetest.core.domain.models.UserModel
import com.composetest.core.data.repositories.SessionRepository
import com.composetest.core.data.repositories.UserRepository
import com.composetest.core.domain.converters.SessionEntityConverter
import com.composetest.core.domain.converters.UserEntityConverter
import com.composetest.core.domain.converters.UserModelConverter
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionUseCaseImpl @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionEntityConverter: SessionEntityConverter,
    private val userEntityConverter: UserEntityConverter,
    private val userModelConverter: UserModelConverter
) : SessionUseCase {
    
    override suspend fun createSession(userModel: UserModel) {
        val newSession = SessionModel(
            initialDate = LocalDateTime.now(),
            user = userModel
        )
        userRepository.insert(userEntityConverter.convertTo(userModel))
        sessionRepository.insert(sessionEntityConverter.convertTo(newSession))
    }

    override fun getCurrentUser() = sessionRepository
        .getCurrentUser(userModelConverter::convertTo)
}