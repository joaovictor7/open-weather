package com.composetest.core.database.repositories

import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.entities.UserEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase
) : UserRepository {

    private val userDao = appDatabase.userDao()

    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    override fun <T> getCurrentUser(mapper: (UserEntity?) -> T) = userDao
        .getCurrentUser()
        .map(mapper::invoke)
}