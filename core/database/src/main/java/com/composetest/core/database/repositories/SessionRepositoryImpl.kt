package com.composetest.core.database.repositories

import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase
) : SessionRepository {

    private val sessionDao =  appDatabase.sessionDao()

    override suspend fun insert(entity: SessionEntity) {
        sessionDao.insert(entity)
    }

    override suspend fun update(entity: EndDateSessionEntityUpdate) {
        sessionDao.update(entity)
    }

    override suspend fun <T> getCurrentSession(mapper: (SessionEntity?) -> T) =
        sessionDao.getCurrentSession().let(mapper)
}