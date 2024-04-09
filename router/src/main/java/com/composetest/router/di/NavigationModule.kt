package com.composetest.router.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.router.providers.NavigationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object NavigationModule {

    @Provides
    @ViewModelScoped
    fun navigation(savedStateHandle: SavedStateHandle) = NavigationProvider(savedStateHandle)

}