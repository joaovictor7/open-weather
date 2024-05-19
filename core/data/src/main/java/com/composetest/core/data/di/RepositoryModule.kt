package com.composetest.core.data.di

import com.composetest.core.data.repositories.LoginRepository
import com.composetest.core.data.repositories.LoginRepositoryImpl
import com.composetest.core.data.repositories.PreferencesDataRepository
import com.composetest.core.data.repositories.PreferencesDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    abstract fun loginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun preferencesDataRepository1(preferencesDataRepositoryImpl: PreferencesDataRepositoryImpl): PreferencesDataRepository
}