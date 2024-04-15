package com.composetest.feature.home.di

import com.composetest.feature.home.navigation.HomeDestination
import com.composetest.router.domain.enums.Destinations
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.navigation.qualifiers.Destination
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
    @Destination(Destinations.HOME)
    abstract fun homeDestination(homeDestination: HomeDestination): ScreenDestination
}