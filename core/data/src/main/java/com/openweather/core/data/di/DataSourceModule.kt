package com.openweather.core.data.di

import com.openweather.core.data.data.datasources.local.PreferenceDataSource
import com.openweather.core.data.data.datasources.local.PreferenceDataSourceImpl
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSource
import com.openweather.core.data.data.datasources.remote.FirebaseAnalyticsDataSourceImpl
import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSource
import com.openweather.core.data.data.datasources.remote.OpenWeatherDataSourceImpl
import com.openweather.core.data.data.datasources.remote.OpenWeatherFakeDataSourceImpl
import com.openweather.core.data.di.qualifiers.Api
import com.openweather.core.data.enums.NetworkApi
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
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProvidesModule {

    @Provides
    fun openWeatherDataSource(
        fakeInstanceProvider: FakeInstanceProvider,
        safeRemoteCallManager: RemoteCallManager,
        @Api(NetworkApi.OPEN_WEATHER) openWeatherApi: HttpClient
    ): OpenWeatherDataSource = fakeInstanceProvider.getInstance(
        instance = OpenWeatherDataSourceImpl(
            openWeatherApi = openWeatherApi,
            safeRemoteCallManager = safeRemoteCallManager
        ),
        fakeInstance = OpenWeatherFakeDataSourceImpl(
            remoteCallManager = safeRemoteCallManager
        )
    )
}