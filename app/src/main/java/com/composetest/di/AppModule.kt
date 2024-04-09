package com.composetest.di

import com.composetest.core.providers.BuildConfigProvider
import com.composetest.providers.BuildConfigProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @get:Provides
    val buildConfigProvider: BuildConfigProvider
        get() = BuildConfigProviderImpl()
}