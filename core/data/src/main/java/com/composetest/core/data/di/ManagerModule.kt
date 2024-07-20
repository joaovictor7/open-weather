package com.composetest.core.data.di

import com.composetest.core.data.managers.RemoteCallManager
import com.composetest.core.data.managers.RemoteCallManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun remoteCallManager(remoteCallManagerImpl: RemoteCallManagerImpl): RemoteCallManager
}