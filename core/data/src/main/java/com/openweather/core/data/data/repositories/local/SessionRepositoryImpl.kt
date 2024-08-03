package com.openweather.core.data.data.repositories.local

import com.openweather.core.data.data.datasources.local.SessionDataSource
import com.openweather.core.database.entities.SessionEntity
import com.openweather.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import javax.inject.Inject

internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource
) : SessionRepository {

    override suspend fun insert(entity: SessionEntity) {
        sessionDataSource.insert(entity)
    }

    override suspend fun finishSession(sessionId: Long) {
        sessionDataSource.update(FinishedSessionEntityUpdate(sessionId, true))
    }

    override suspend fun <T> getCurrentSession(
        mapper: (SessionEntity?) -> T
    ) = sessionDataSource.getCurrentSession().let(mapper)
}