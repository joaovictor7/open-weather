package com.composetest.core.data.repositories.local

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepositoryImpl @Inject constructor(
    dataSource: DatabaseDataSource
) : SessionRepository {

    private val sessionDao = dataSource.appDatabase.sessionDao()

    override suspend fun insert(entity: SessionEntity) {
        sessionDao.insert(entity)
    }

    override suspend fun update(entity: EndDateSessionEntityUpdate) {
        sessionDao.update(entity)
    }

    override fun <T> getCurrentSession(mapper: (SessionEntity?) -> T) = sessionDao
        .getCurrentSession()
        .map(mapper::invoke)
}