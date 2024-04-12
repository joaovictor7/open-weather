package com.composetest.feature.login.di

import com.composetest.feature.login.destinations.LoginDestination
import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {
    @Binds
    @Singleton
    @Destinations(Destination.LOGIN)
    abstract fun loginDestination(loginDestinations: LoginDestination): ScreenDestination
}