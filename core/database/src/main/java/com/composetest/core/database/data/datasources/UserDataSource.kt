package com.composetest.core.database.data.datasources

import com.composetest.core.database.entities.UserEntity

internal interface UserDataSource {
    suspend fun insert(user: UserEntity)
    suspend fun getCurrentUser(): UserEntity?
}