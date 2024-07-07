package com.composetest.common.di

import com.composetest.common.enums.BuildType
import com.composetest.common.enums.Flavor
import com.composetest.common.providers.DateTimeProvider
import com.composetest.common.providers.DateTimeProviderImpl
import com.composetest.common.providers.FakeInstanceProvider
import com.composetest.common.providers.FakeInstanceProviderImpl
import com.composetest.common.providers.RemoteCallProvider
import com.composetest.common.providers.RemoteCallProviderImpl
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.providers.fields.buildtypes.BuildTypeFieldsProvider
import com.composetest.common.providers.fields.buildtypes.BuildTypeFieldsProviderDebugImpl
import com.composetest.common.providers.fields.buildtypes.BuildTypeFieldsProviderReleaseImpl
import com.composetest.common.providers.fields.buildtypes.BuildTypeFieldsProviderStagingImpl
import com.composetest.common.providers.fields.flavors.FlavorFieldsProvider
import com.composetest.common.providers.fields.flavors.FlavorFieldsProviderFreeImpl
import com.composetest.common.providers.fields.flavors.FlavorFieldsProviderFullImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderBindsModule {
    @Binds
    abstract fun fakeInstanceProvider(fakeInstanceProviderImpl: FakeInstanceProviderImpl): FakeInstanceProvider

    @Binds
    abstract fun remoteCallProvider(remoteCallProviderImpl: RemoteCallProviderImpl): RemoteCallProvider

    @Binds
    abstract fun dateTimeProvider(dateTimeProviderImpl: DateTimeProviderImpl): DateTimeProvider
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