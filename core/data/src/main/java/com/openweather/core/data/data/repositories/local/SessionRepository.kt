package com.openweather.core.data.data.repositories.local

import com.openweather.core.database.entities.SessionEntity

interface SessionRepository {
    suspend fun insert(entity: SessionEntity)
    suspend fun finishSession(sessionId: Long)
    suspend fun <T> getCurrentSession(mapper: (SessionEntity?) -> T): T
}