package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.database.domain.entities.UserEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    dataSource: DatabaseDataSource
) : UserRepository {

    private val userDao = dataSource.appDatabase.userDao()

    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }

    override fun <T> getCurrentUser(converter: (UserEntity) -> T) = userDao
        .getCurrentUser()
        .map(converter::invoke)
}