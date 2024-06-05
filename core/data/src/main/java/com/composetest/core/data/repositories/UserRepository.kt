package com.composetest.core.data.repositories

import com.composetest.core.database.domain.entities.UserEntity

interface UserRepository {
    suspend fun insert(user: UserEntity)
}