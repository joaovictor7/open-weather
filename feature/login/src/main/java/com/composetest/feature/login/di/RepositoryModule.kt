package com.composetest.feature.login.di

import com.composetest.feature.login.infra.datasource.LoginDataSource
import com.composetest.feature.login.infra.repositories.LoginRepository
import com.composetest.feature.login.infra.repositories.LoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    @ViewModelScoped
    fun loginRepository(loginDataSource: LoginDataSource): LoginRepository =
        LoginRepositoryImpl(loginDataSource)
}