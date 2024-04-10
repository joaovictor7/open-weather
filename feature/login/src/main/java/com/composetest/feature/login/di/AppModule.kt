package com.composetest.feature.login.di

import com.composetest.feature.login.destinations.LoginDestination
import com.composetest.router.destinations.LoginDestinations
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
    val login: LoginDestinations.Login get() = LoginDestination
}