package com.openweather.core.data.data.repositories.local

import com.openweather.core.data.data.datasources.local.UserDataSource
import com.openweather.core.database.entities.UserEntity
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun insert(user: UserEntity) {
        userDataSource.insert(user)
    }

    override suspend fun <T> getCurrentUser(mapper: (UserEntity?) -> T) =
        userDataSource.getCurrentUser().let(mapper)
}