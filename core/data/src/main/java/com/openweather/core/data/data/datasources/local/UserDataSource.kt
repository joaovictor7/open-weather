package com.openweather.core.data.data.datasources.local

import com.openweather.core.database.entities.UserEntity

internal interface UserDataSource {
    suspend fun insert(user: UserEntity)
    suspend fun getCurrentUser(): UserEntity?
}