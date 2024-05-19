package com.composetest.core.data.di

import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.datasources.remote.LoginDataSourceFakeImpl
import com.composetest.core.data.datasources.remote.LoginDataSourceImpl
import com.composetest.core.data.providers.DataSourceProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    fun loginDataSource(
        dataSourceProvider: DataSourceProvider,
        firebaseAuth: FirebaseAuth
    ): LoginDataSource = dataSourceProvider.getDataSource(
        dataSource = LoginDataSourceImpl(firebaseAuth),
        fakeDataSource = LoginDataSourceFakeImpl()
    )
}