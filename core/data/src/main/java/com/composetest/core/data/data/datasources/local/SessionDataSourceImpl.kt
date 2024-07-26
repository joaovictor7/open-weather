package com.composetest.core.data.data.datasources.local

import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import com.composetest.core.database.daos.SessionEntityDao
import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.FinishedSessionEntityUpdate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SessionDataSourceImpl @Inject constructor(
    private val sessionEntityDao: SessionEntityDao,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : SessionDataSource {

    override suspend fun insert(entity: SessionEntity) = withContext(ioDispatcher) {
        sessionEntityDao.insert(entity)
    }

    override suspend fun update(entity: FinishedSessionEntityUpdate) = withContext(ioDispatcher) {
        sessionEntityDao.update(entity)
    }

    override suspend fun getCurrentSession() = withContext(ioDispatcher) {
        sessionEntityDao.getCurrentSession()
    }
}