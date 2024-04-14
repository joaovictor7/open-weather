package com.composetest.di

import com.composetest.core.managers.BuildConfigManager
import com.composetest.managers.BuildConfigManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ManagerModule {
    @Binds
    @Singleton
    abstract fun buildConfigManager(buildConfigManagerImpl: BuildConfigManagerImpl): BuildConfigManager
}