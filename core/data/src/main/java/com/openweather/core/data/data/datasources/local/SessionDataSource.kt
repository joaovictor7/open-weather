package com.openweather.core.data.data.datasources.local

import com.openweather.core.database.entities.SessionEntity
import com.openweather.core.database.entities.partialupdate.FinishedSessionEntityUpdate

internal interface SessionDataSource {
    suspend fun insert(entity: SessionEntity)

    suspend fun update(entity: FinishedSessionEntityUpdate)

    suspend fun getCurrentSession(): SessionEntity?
}