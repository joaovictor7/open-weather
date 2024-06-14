package com.composetest.core.router.di

import com.composetest.core.router.providers.NavControllerProvider
import com.composetest.core.router.providers.NavControllerProviderImpl
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.core.router.providers.NavigationProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderSingletonModule {
    @Binds
    abstract fun navControllerProvider(navControllerProviderImpl: NavControllerProviderImpl): NavControllerProvider
}

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class ProviderViewModelModule {
    @Binds
    abstract fun navigationProvider(navigationProviderImpl: NavigationProviderImpl): NavigationProvider
}