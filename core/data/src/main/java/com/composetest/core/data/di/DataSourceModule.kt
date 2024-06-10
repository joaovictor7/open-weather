package com.composetest.core.data.di

import com.composetest.common.providers.DataSourceProvider
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSourceFakeImpl
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSourceImpl
import com.composetest.core.data.converters.AuthenticationResponseConverter
import com.composetest.common.providers.RemoteCallProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    fun firebaseAuthDataSource(
        remoteCallProvider: RemoteCallProvider,
        dataSourceProvider: DataSourceProvider,
        firebaseAuth: FirebaseAuth,
        authenticationResponseConverter: AuthenticationResponseConverter
    ): FirebaseAuthDataSource = dataSourceProvider.getDataSource(
        dataSource = FirebaseAuthDataSourceImpl(
            firebaseAuth = firebaseAuth,
            authenticationResponseConverter = authenticationResponseConverter,
            remoteCallProvider = remoteCallProvider
        ),
        fakeDataSource = FirebaseAuthDataSourceFakeImpl(remoteCallProvider)
    )
}