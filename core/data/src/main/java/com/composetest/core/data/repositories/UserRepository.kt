package com.composetest.core.data.repositories

import com.composetest.core.database.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insert(user: UserEntity)
    fun <T> getCurrentUser(converter: (UserEntity) -> T): Flow<T>
}