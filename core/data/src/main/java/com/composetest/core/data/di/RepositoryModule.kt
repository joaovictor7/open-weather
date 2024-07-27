package com.composetest.core.data.di

import com.composetest.core.data.data.repositories.local.AppThemeRepository
import com.composetest.core.data.data.repositories.local.AppThemeRepositoryImpl
import com.composetest.core.data.data.repositories.local.SessionRepository
import com.composetest.core.data.data.repositories.local.SessionRepositoryImpl
import com.composetest.core.data.data.repositories.local.UserRepository
import com.composetest.core.data.data.repositories.local.UserRepositoryImpl
import com.composetest.core.data.data.repositories.local.WorkManagerRepository
import com.composetest.core.data.data.repositories.local.WorkManagerRepositoryImpl
import com.composetest.core.data.data.repositories.remote.AnalyticsRepository
import com.composetest.core.data.data.repositories.remote.AnalyticsRepositoryImpl
import com.composetest.core.data.data.repositories.remote.AuthenticationRepository
import com.composetest.core.data.data.repositories.remote.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    // Locals
    @Binds
    @Singleton
    abstract fun appThemeRepository(
        appThemeRepositoryImpl: AppThemeRepositoryImpl
    ): AppThemeRepository

    @Binds
    abstract fun workManagerRepository(
        workManagerRepositoryImpl: WorkManagerRepositoryImpl
    ): WorkManagerRepository

    @Binds
    abstract fun analyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl
    ): AnalyticsRepository

    @Binds
    abstract fun sessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    abstract fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    // Remotes
    @Binds
    abstract fun authenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}