package com.composetest.core.data.data.repositories.local

import com.composetest.core.data.data.datasources.local.SessionDataSource
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource
) : SessionRepository {

    override suspend fun insert(entity: SessionEntity) {
        sessionDataSource.insert(entity)
    }

    override suspend fun update(entity: FinishedSessionEntityUpdate) {
        sessionDataSource.update(entity)
    }

    override suspend fun <T> getCurrentSession(
        mapper: (SessionEntity?) -> T
    ) = sessionDataSource.getCurrentSession().let(mapper)
}