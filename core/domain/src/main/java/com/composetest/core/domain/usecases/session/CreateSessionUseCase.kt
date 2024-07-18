package com.composetest.core.domain.usecases.session

import com.composetest.core.database.data.repositories.SessionRepository
import com.composetest.core.database.data.repositories.UserRepository
import com.composetest.core.domain.mappers.SessionEntityMapper
import com.composetest.core.domain.mappers.UserEntityMapper
import com.composetest.core.domain.models.session.SessionWithUserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreateSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val userRepository: UserRepository,
    private val sessionEntityMapper: SessionEntityMapper,
    private val userEntityMapper: UserEntityMapper
) {

    suspend operator fun invoke(sessionWithUser: SessionWithUserModel) {
        userRepository.insert(userEntityMapper(sessionWithUser.user))
        sessionRepository.insert(sessionEntityMapper(sessionWithUser))
    }
}