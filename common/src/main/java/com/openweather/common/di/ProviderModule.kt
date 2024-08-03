package com.openweather.common.di

import com.openweather.common.enums.BuildType
import com.openweather.common.enums.Flavor
import com.openweather.common.providers.DateTimeProvider
import com.openweather.common.providers.DateTimeProviderImpl
import com.openweather.common.providers.BuildConfigProvider
import com.openweather.common.providers.NetworkProvider
import com.openweather.common.providers.NetworkProviderImpl
import com.openweather.common.providers.fields.buildtypes.BuildTypeFieldsProvider
import com.openweather.common.providers.fields.buildtypes.BuildTypeFieldsProviderDebugImpl
import com.openweather.common.providers.fields.buildtypes.BuildTypeFieldsProviderReleaseImpl
import com.openweather.common.providers.fields.buildtypes.BuildTypeFieldsProviderStagingImpl
import com.openweather.common.providers.fields.flavors.FlavorFieldsProvider
import com.openweather.common.providers.fields.flavors.FlavorFieldsProviderFreeImpl
import com.openweather.common.providers.fields.flavors.FlavorFieldsProviderFullImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderBindsModule {
    @Binds
    abstract fun dateTimeProvider(dateTimeProviderImpl: DateTimeProviderImpl): DateTimeProvider

    @Binds
    abstract fun networkProvider(networkProviderImpl: NetworkProviderImpl): NetworkProvider
}

@Module
@InstallIn(SingletonComponent::class)
internal object ProviderProvidesModule {
    @Provides
    fun buildFieldsProvider(buildConfigProvider: BuildConfigProvider): BuildTypeFieldsProvider =
        when (buildConfigProvider.get.buildType) {
            BuildType.RELEASE -> BuildTypeFieldsProviderReleaseImpl()
            BuildType.STAGING -> BuildTypeFieldsProviderStagingImpl()
            BuildType.DEBUG -> BuildTypeFieldsProviderDebugImpl()
        }

    @Provides
    fun flavorFieldsProvider(buildConfigProvider: BuildConfigProvider): FlavorFieldsProvider =
        when (buildConfigProvider.get.flavor) {
            Flavor.FULL -> FlavorFieldsProviderFullImpl()
            Flavor.FREE -> FlavorFieldsProviderFreeImpl()
        }
}