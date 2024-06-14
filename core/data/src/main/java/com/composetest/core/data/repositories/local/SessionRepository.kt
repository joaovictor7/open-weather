package com.composetest.core.data.repositories.local

import com.composetest.core.database.domain.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insert(entity: SessionEntity)
    fun <T> getCurrentSession(mapper: (SessionEntity) -> T): Flow<T>
}