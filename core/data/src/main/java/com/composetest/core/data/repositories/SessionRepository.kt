package com.composetest.core.data.repositories

import com.composetest.core.database.domain.entities.SessionEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insert(entity: SessionEntity)
    fun <T> getCurrentSession(converter: (SessionEntity) -> T): Flow<T>
}