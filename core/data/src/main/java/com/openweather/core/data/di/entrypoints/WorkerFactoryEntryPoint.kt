package com.openweather.core.data.di.entrypoints

import androidx.hilt.work.HiltWorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WorkerFactoryEntryPoint {
    fun workerFactory(): HiltWorkerFactory
}