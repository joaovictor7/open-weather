package com.composetest.router.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.router.providers.NavControllerComponentBuilder
import com.composetest.router.providers.NavControllerProvider
import com.composetest.router.providers.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    @ViewModelScoped
    fun navigationProvider(
        navControllerProvider: NavControllerProvider,
        savedStateHandle: SavedStateHandle
    ) = NavigationProvider(navControllerProvider, savedStateHandle)
}

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {
    @Provides
    @Singleton
    fun navControllerProvider(builder: NavControllerComponentBuilder) = NavControllerProvider(builder)
}

