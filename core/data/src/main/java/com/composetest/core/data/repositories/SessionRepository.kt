package com.composetest.core.data.repositories

import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun insert(entity: SessionEntity)
    fun <T> getCurrentUser(converter: (UserEntity) -> T): Flow<T>
}