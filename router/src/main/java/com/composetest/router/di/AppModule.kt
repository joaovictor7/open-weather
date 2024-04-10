package com.composetest.router.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.router.di.components.NavControllerComponentBuilder
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
object AppModule {

    @Provides
    @ViewModelScoped
    fun navigation(teste: NavControllerProvider, savedStateHandle: SavedStateHandle) =
        NavigationProvider(teste, savedStateHandle)

}

@Module
@InstallIn(SingletonComponent::class)
object NavigationSingleModule {
    @Provides
    @Singleton
    fun getTest(builder: NavControllerComponentBuilder) = NavControllerProvider(builder)
}

