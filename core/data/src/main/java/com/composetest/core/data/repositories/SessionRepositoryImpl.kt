package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.DatabaseDataSource
import com.composetest.core.database.domain.entities.SessionEntity
import com.composetest.core.database.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepositoryImpl @Inject constructor(
    private val dataSource: DatabaseDataSource
) : SessionRepository {

    override fun insert(entity: SessionEntity) {
        dataSource.appDatabase
            .sessionDao()
            .insert(entity)
    }

    override fun <T> getCurrentUser(converter: (UserEntity) -> T): Flow<T> {
        TODO("Not yet implemented")
    }
}