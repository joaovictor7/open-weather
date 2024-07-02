package com.composetest.core.data.repositories.local

import com.composetest.core.database.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insert(user: UserEntity)
    fun <T> getCurrentUser(mapper: (UserEntity?) -> T): Flow<T>
}