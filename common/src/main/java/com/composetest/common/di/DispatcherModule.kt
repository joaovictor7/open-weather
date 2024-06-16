package com.composetest.common.di

import com.composetest.common.di.qualifiers.DefaultDispatcher
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.di.qualifiers.MainDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {
    @DefaultDispatcher
    @Provides
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
