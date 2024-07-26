package com.composetest.core.data.data.datasources.local

import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.entities.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userEntityDao: UserEntityDao,
    @Dispatcher(Dispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : UserDataSource {

    override suspend fun insert(user: UserEntity) = withContext(ioDispatcher) {
        userEntityDao.insert(user)
    }

    override suspend fun getCurrentUser() = withContext(ioDispatcher) {
        userEntityDao.getCurrentUser()
    }
}