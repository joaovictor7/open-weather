package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.data.domain.converters.UserEntityConverter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class UserRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource,
    private val converter: UserEntityConverter
) : UserRepository {

}