package com.composetest.core.database.data.datasources

import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionDataSourceImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    appDatabase: AppDatabase
) : SessionDataSource {
    private val sessionDao = appDatabase.sessionDao()

    override suspend fun insert(entity: SessionEntity) = withContext(ioDispatcher) {
        sessionDao.insert(entity)
    }

    override suspend fun update(entity: EndDateSessionEntityUpdate) = withContext(ioDispatcher) {
        sessionDao.update(entity)
    }

    override suspend fun getCurrentSession() = withContext(ioDispatcher) {
        sessionDao.getCurrentSession()
    }
}