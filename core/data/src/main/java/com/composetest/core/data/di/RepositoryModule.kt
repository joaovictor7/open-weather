package com.composetest.core.data.di

import com.composetest.core.data.repositories.AppThemeRepository
import com.composetest.core.data.repositories.AuthenticationRepositoryImpl
import com.composetest.core.data.repositories.AppThemeRepositoryImpl
import com.composetest.core.data.repositories.AuthenticationRepository
import com.composetest.core.data.repositories.SessionRepository
import com.composetest.core.data.repositories.SessionRepositoryImpl
import com.composetest.core.data.repositories.UserRepository
import com.composetest.core.data.repositories.UserRepositoryImpl
import com.composetest.core.data.repositories.WorkManagerRepository
import com.composetest.core.data.repositories.WorkManagerRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun loginRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

    @Binds
    abstract fun appThemeRepository(appThemeRepositoryImpl: AppThemeRepositoryImpl): AppThemeRepository

    @Binds
    abstract fun sessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    abstract fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun workManagerRepository(workManagerRepositoryImpl: WorkManagerRepositoryImpl): WorkManagerRepository
}