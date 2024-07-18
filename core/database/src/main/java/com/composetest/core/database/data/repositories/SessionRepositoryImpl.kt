package com.composetest.core.database.data.repositories

import com.composetest.core.database.data.datasources.SessionDataSource
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepositoryImpl @Inject constructor(
    private val sessionDataSource: SessionDataSource
) : SessionRepository {

    override suspend fun insert(entity: SessionEntity) {
        sessionDataSource.insert(entity)
    }

    override suspend fun update(entity: EndDateSessionEntityUpdate) {
        sessionDataSource.update(entity)
    }

    override suspend fun <T> getCurrentSession(
        mapper: (SessionEntity?) -> T
    ) = sessionDataSource.getCurrentSession().let(mapper)
}