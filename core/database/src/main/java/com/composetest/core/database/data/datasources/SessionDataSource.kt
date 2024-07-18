package com.composetest.core.database.data.datasources

import com.composetest.core.database.entities.SessionEntity
import com.composetest.core.database.entities.partialupdate.EndDateSessionEntityUpdate

internal interface SessionDataSource {
    suspend fun insert(entity: SessionEntity)

    suspend fun update(entity: EndDateSessionEntityUpdate)

    suspend fun getCurrentSession(): SessionEntity?
}