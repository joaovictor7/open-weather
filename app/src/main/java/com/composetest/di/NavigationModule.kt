package com.composetest.di

import com.composetest.feature.home.navigation.HomeDestination
import com.composetest.feature.login.navigation.LoginDestination
import com.composetest.router.navigation.ScreenDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {
    @Provides
    @Singleton
    fun allDestinations(): Array<ScreenDestination> = arrayOf(
        LoginDestination,
        HomeDestination
    )
}