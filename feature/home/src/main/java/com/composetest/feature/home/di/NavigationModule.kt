package com.composetest.feature.home.di

import com.composetest.feature.home.destinations.HomeDestination
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
    @Destinations(Destination.HOME)
    abstract fun homeDestination(homeDestination: HomeDestination): ScreenDestination
}