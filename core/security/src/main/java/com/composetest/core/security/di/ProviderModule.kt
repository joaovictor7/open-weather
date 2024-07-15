package com.composetest.core.security.di

import com.composetest.core.security.providers.SecureSharedPreferencesProvider
import com.composetest.core.security.providers.SecureSharedPreferencesProviderImpl
import com.composetest.core.security.providers.SqlCipherProvider
import com.composetest.core.security.providers.SqlCipherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ProviderModule {

    @Binds
    abstract fun sqlCipherProvider(sqlCipherProviderImpl: SqlCipherProviderImpl): SqlCipherProvider

    @Binds
    abstract fun secureSharedPreferencesProvider(
        secureSharedPreferencesProviderImpl: SecureSharedPreferencesProviderImpl
    ): SecureSharedPreferencesProvider
}