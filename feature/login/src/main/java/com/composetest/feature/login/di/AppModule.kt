package com.composetest.feature.login.di

import com.composetest.core.providers.DataSourceProvider
import com.composetest.feature.login.destinations.LoginDestination
import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.infra.datasource.api.LoginDataSourceApiImpl
import com.composetest.feature.login.infra.datasource.mock.LoginDataSourceMockImpl
import com.composetest.feature.login.infra.repositories.LoginRepository
import com.composetest.feature.login.usecases.LoginUseCase
import com.composetest.router.destinations.LoginDestinations
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @get:Provides
    @Singleton
    val firebaseAuth: FirebaseAuth get() = FirebaseAuth.getInstance()

    @get:Provides
    @Singleton
    val loginDestination: LoginDestinations.Login get() = LoginDestination
}

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun loginDataSource(
        dataSourceProvider: DataSourceProvider,
        firebaseAuth: FirebaseAuth
    ): LoginDataSource = dataSourceProvider.getDataSource(
        apiDataSource = LoginDataSourceApiImpl(firebaseAuth),
        mockDataSource = LoginDataSourceMockImpl()
    )

    @Provides
    @ViewModelScoped
    fun loginRepository(loginDataSource: LoginDataSource) = LoginRepository(loginDataSource)

    @Provides
    @ViewModelScoped
    fun loginUseCase(loginRepository: LoginRepository) = LoginUseCase(loginRepository)
}