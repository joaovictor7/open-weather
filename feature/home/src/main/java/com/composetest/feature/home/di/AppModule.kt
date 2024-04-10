package com.composetest.feature.home.di

import com.composetest.feature.home.destinations.HomeDestination
import com.composetest.router.destinations.HomeDestinations
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @get:Provides
    @Singleton
    val login: HomeDestinations.Home get() = HomeDestination

}