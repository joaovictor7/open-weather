package com.composetest.core.domain.usecases.session

import com.composetest.core.database.data.repositories.SessionRepository
import com.composetest.core.domain.mappers.SessionModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNeedsLoginBySessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val sessionModelMapper: SessionModelMapper
) {

    suspend operator fun invoke() = sessionRepository
        .getCurrentSession(sessionModelMapper::invoke).let { currentSession ->
            currentSession == null
        }
}