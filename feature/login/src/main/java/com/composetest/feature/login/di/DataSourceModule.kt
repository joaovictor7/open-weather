package com.composetest.feature.login.di

import com.composetest.core.providers.DataSourceProvider
import com.composetest.feature.login.data.datasources.LoginDataSource
import com.composetest.feature.login.data.datasources.api.LoginDataSourceApiImpl
import com.composetest.feature.login.data.datasources.mock.LoginDataSourceMockImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal object DataSourceModule {
    @Provides
    @ViewModelScoped
    fun loginDataSource(
        dataSourceProvider: DataSourceProvider,
        firebaseAuth: FirebaseAuth
    ): LoginDataSource = dataSourceProvider.getDataSource(
        apiDataSource = LoginDataSourceApiImpl(firebaseAuth),
        mockDataSource = LoginDataSourceMockImpl()
    )
}