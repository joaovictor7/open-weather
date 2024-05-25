package com.composetest.core.data.di

import com.composetest.core.data.repositories.LoginRepository
import com.composetest.core.data.repositories.LoginRepositoryImpl
import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.data.repositories.AppThemeRepositoryImpl
import com.composetest.core.data.repositories.UserRepository
import com.composetest.core.data.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun loginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun appThemeRepository(appThemeRepositoryImpl: AppThemeRepositoryImpl): AppThemeRepository

    @Binds
    abstract fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}