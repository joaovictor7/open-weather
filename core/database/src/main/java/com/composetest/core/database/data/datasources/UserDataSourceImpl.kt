package com.composetest.core.database.data.datasources

import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.core.database.database.AppDatabase
import com.composetest.core.database.entities.UserEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserDataSourceImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    appDatabase: AppDatabase
) : UserDataSource {

    private val userDao = appDatabase.userDao()

    override suspend fun insert(user: UserEntity) = withContext(ioDispatcher) {
        userDao.insert(user)
    }

    override suspend fun getCurrentUser() = withContext(ioDispatcher) {
        userDao.getCurrentUser()
    }
}