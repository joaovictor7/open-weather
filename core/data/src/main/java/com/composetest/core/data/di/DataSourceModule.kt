package com.composetest.core.data.di

import android.content.Context
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSource
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSourceFakeImpl
import com.composetest.core.data.datasources.remote.FirebaseAuthDataSourceImpl
import com.composetest.core.data.providers.DataSourceProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {
    @Provides
    fun firebaseAuthDataSource(
        @ApplicationContext context: Context,
        dataSourceProvider: DataSourceProvider,
        firebaseAuth: FirebaseAuth
    ): FirebaseAuthDataSource = dataSourceProvider.getDataSource(
        dataSource = FirebaseAuthDataSourceImpl(firebaseAuth, context),
        fakeDataSource = FirebaseAuthDataSourceFakeImpl(context)
    )
}