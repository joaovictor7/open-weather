package com.composetest.core.data.di

import com.composetest.core.data.datasources.remote.LoginDataSource
import com.composetest.core.data.datasources.fake.LoginDataSourceFakeImpl
import com.composetest.core.data.datasources.remote.LoginDataSourceApiImpl
import com.composetest.core.data.providers.DataSourceProvider
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
        mockDataSource = LoginDataSourceFakeImpl()
    )
}