package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.common.providers.RemoteCallProvider
import com.composetest.common.providers.FakeInstanceProvider
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.repositories.local.AppThemeRepository
import com.composetest.core.data.repositories.remote.AuthenticationRepositoryImpl
import com.composetest.core.data.repositories.local.AppThemeRepositoryImpl
import com.composetest.core.data.repositories.local.SessionRepository
import com.composetest.core.data.repositories.local.SessionRepositoryImpl
import com.composetest.core.data.repositories.local.UserRepository
import com.composetest.core.data.repositories.local.UserRepositoryImpl
import com.composetest.core.data.repositories.local.WorkManagerRepository
import com.composetest.core.data.repositories.local.WorkManagerRepositoryImpl
import com.composetest.core.data.repositories.remote.AnalyticsRepository
import com.composetest.core.data.repositories.remote.AnalyticsRepositoryImpl
import com.composetest.core.data.repositories.remote.AuthenticationRepositoryFakeImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryBindsModule {
    @Binds
    abstract fun appThemeRepository(appThemeRepositoryImpl: AppThemeRepositoryImpl): AppThemeRepository

    @Binds
    abstract fun sessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository

    @Binds
    abstract fun userRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun workManagerRepository(workManagerRepositoryImpl: WorkManagerRepositoryImpl): WorkManagerRepository

    @Binds
    abstract fun analyticsRepository(analyticsRepositoryImpl: AnalyticsRepositoryImpl): AnalyticsRepository
}

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryProvidesModule {
    @Provides
    fun authenticationRepository(
        fakeInstanceProvider: FakeInstanceProvider,
        remoteCallProvider: RemoteCallProvider,
        dateTimeProvider: DateTimeProvider,
        firebaseAuthDataSource: FirebaseAuthDataSource
    ) = fakeInstanceProvider.getInstance(
        instance = AuthenticationRepositoryImpl(
            remoteCallProvider = remoteCallProvider,
            firebaseAuthDataSource = firebaseAuthDataSource
        ),
        fakeInstance = AuthenticationRepositoryFakeImpl(
            remoteCallProvider = remoteCallProvider,
            dateTimeProvider = dateTimeProvider
        )
    )
}