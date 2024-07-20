package com.composetest.core.router.di

import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.router.managers.NavigationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ManagerModule {

    @Binds
    abstract fun navigationManager(navigationManagerImpl: NavigationManagerImpl): NavigationManager
}