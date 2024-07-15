package com.composetest.core.data.di

import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.providers.FakeInstanceProvider
import com.composetest.core.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.providers.RemoteCallProvider
import com.composetest.core.data.repositories.remote.AuthenticationRepositoryFakeImpl
import com.composetest.core.data.repositories.remote.AuthenticationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryProvidesModule {
    @Provides
    fun authenticationRepository(
        fakeInstanceProvider: FakeInstanceProvider,
        remoteCallProvider: RemoteCallProvider,
        dateTimeProvider: DateTimeProvider,
        authenticationDataSource: AuthenticationDataSourceImpl
    ) = fakeInstanceProvider.getInstance(
        instance = AuthenticationRepositoryImpl(
            remoteCallProvider = remoteCallProvider,
            authenticationDataSource = authenticationDataSource
        ),
        fakeInstance = AuthenticationRepositoryFakeImpl(
            remoteCallProvider = remoteCallProvider,
            dateTimeProvider = dateTimeProvider
        )
    )
}