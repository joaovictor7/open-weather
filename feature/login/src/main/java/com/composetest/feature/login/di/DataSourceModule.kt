package com.composetest.feature.login.di

import com.composetest.core.managers.DataSourceManager
import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.infra.datasource.api.LoginDataSourceApiImpl
import com.composetest.feature.login.infra.datasource.mock.LoginDataSourceMockImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {
    @Provides
    @ViewModelScoped
    fun loginDataSource(
        dataSourceManager: DataSourceManager,
        firebaseAuth: FirebaseAuth
    ): LoginDataSource = dataSourceManager.getDataSource(
        apiDataSource = LoginDataSourceApiImpl(firebaseAuth),
        mockDataSource = LoginDataSourceMockImpl()
    )
}