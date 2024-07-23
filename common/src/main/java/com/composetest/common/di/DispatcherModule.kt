package com.composetest.common.di

import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers as Coroutine

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {
    @Provides
    @Dispatcher(Dispatchers.Default)
    fun defaultDispatcher(): CoroutineDispatcher = Coroutine.Default

    @Provides
    @Dispatcher(Dispatchers.IO)
    fun ioDispatcher(): CoroutineDispatcher = Coroutine.IO

    @Provides
    @Dispatcher(Dispatchers.Main)
    fun mainDispatcher(): CoroutineDispatcher = Coroutine.Main
}
