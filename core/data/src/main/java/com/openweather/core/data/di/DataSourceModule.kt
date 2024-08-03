package com.openweather.core.data.di

import com.openweather.common.providers.DateTimeProvider
import com.openweather.core.data.data.datasources.local.PreferenceDataSource
import com.openweather.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.openweather.core.data.data.datasources.local.SessionDataSource
import com.openweather.core.data.data.datasources.local.SessionDataSourceImpl
import com.openweather.core.data.data.datasources.local.UserDataSource
import com.openweather.core.data.data.datasources.local.UserDataSourceImpl
import com.openweather.core.data.data.datasources.remote.AuthenticationDataSource
import com.openweather.core.data.data.datasources.remote.AuthenticationDataSourceImpl
import com.openweather.core.data.data.datasources.remote.AuthenticationFakeDataSourceImpl
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSource
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.openweather.core.data.managers.RemoteCallManager
import com.openweather.core.data.providers.FakeInstanceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceBindsModule {

    @Binds
    abstract fun firebaseAnalyticsDataSource(
        firebaseAnalyticsDataSourceImpl: FirebaseAnalyticsDataSourceImpl
    ): FirebaseAnalyticsDataSource

    @Binds
    abstract fun preferenceDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource

    @Binds
    abstract fun userDataSource(userDataSourceImpl: UserDataSourceImpl): UserDataSource

    @Binds
    abstract fun sessionDataSource(sessionDataSourceImpl: SessionDataSourceImpl): SessionDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

    @Provides
    fun authenticationDataSource(
        fakeInstanceProvider: FakeInstanceProvider,
        httpClient: HttpClient,
        dateTimeProvider: DateTimeProvider,
        safeRemoteCallManager: RemoteCallManager
    ): AuthenticationDataSource = fakeInstanceProvider.getInstance(
        instance = AuthenticationDataSourceImpl(
            httpClient = httpClient,
            safeRemoteCallManager = safeRemoteCallManager
        ),
        fakeInstance = AuthenticationFakeDataSourceImpl(
            dateTimeProvider = dateTimeProvider,
            safeRemoteCallManager = safeRemoteCallManager
        )
    )
}