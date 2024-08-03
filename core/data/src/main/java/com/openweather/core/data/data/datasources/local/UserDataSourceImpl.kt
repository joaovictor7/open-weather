package com.openweather.core.data.data.datasources.local

import com.openweather.core.database.daos.UserEntityDao
import com.openweather.core.database.entities.UserEntity
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userEntityDao: UserEntityDao
) : UserDataSource {

    override suspend fun insert(user: UserEntity) {
        userEntityDao.insert(user)
    }

    override suspend fun getCurrentUser() = userEntityDao.getCurrentUser()
}