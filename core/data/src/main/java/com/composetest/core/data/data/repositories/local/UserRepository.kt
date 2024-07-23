package com.composetest.core.data.data.repositories.local

import com.composetest.core.database.entities.UserEntity

interface UserRepository {
    suspend fun insert(user: UserEntity)
    suspend fun <T> getCurrentUser(mapper: (UserEntity?) -> T): T
}