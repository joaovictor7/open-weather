package com.openweather.core.security.di

import com.openweather.core.security.providers.CipherProvider
import com.openweather.core.security.providers.CipherProviderImpl
import com.openweather.core.security.providers.SqliteCipherProvider
import com.openweather.core.security.providers.SqliteCipherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun cipherProvider(
        cipherProviderImpl: CipherProviderImpl
    ): CipherProvider

    @Binds
    abstract fun sqlCipherProvider(
        sqliteCipherProviderImpl: SqliteCipherProviderImpl
    ): SqliteCipherProvider
}