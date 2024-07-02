package com.composetest.core.data.repositories.local

import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insert(entity: SessionEntity)
    suspend fun update(entity: EndDateSessionEntityUpdate)
    fun <T> getCurrentSession(mapper: (SessionEntity?) -> T): Flow<T>
}