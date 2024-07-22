package com.composetest.core.data.di

import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.providers.DateTimeProvider
import com.composetest.core.data.data.datasources.local.PreferenceDataSource
import com.composetest.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.composetest.core.data.data.datasources.remote.AuthenticationDataSource
import com.composetest.core.data.data.datasources.remote.AuthenticationDataSourceImpl
import com.composetest.core.data.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.composetest.core.data.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.composetest.core.data.providers.FakeInstanceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindsModule {

    @Binds
    abstract fun firebaseAnalyticsDataSource(firebaseAnalyticsDataSourceImpl: FirebaseAnalyticsDataSourceImpl): FirebaseAnalyticsDataSource

    @Binds
    abstract fun preferenceDataSource(preferenceDataSourceImpl: PreferenceDataSourceImpl): PreferenceDataSource
}


@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

    @Provides
    fun authenticationDataSource(
        fakeInstanceProvider: FakeInstanceProvider,
        httpClient: HttpClient,
        dateTimeProvider: DateTimeProvider,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AuthenticationDataSource = fakeInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            httpClient = httpClient,
            ioDispatcher = ioDispatcher
        ),
        fakeInstance =  AuthenticationFakeDataSourceImpl(
            dateTimeProvider = dateTimeProvider,
            ioDispatcher = ioDispatcher
        )
    )
}