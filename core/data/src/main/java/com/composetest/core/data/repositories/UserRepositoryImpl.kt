package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.database.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource
) : UserRepository {

    override fun insert(user: UserEntity) {
        dataSource.appDatabase
            .userDao()
            .insert(user)
    }
}