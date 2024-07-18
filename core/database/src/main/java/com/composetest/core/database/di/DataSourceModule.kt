package com.composetest.core.database.di

import com.composetest.core.database.data.datasources.SessionDataSource
import com.composetest.core.database.data.datasources.SessionDataSourceImpl
import com.composetest.core.database.data.datasources.UserDataSource
import com.composetest.core.database.data.datasources.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    abstract fun userDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun sessionDataSource(sessionDataSourceImpl: SessionDataSourceImpl): SessionDataSource
}