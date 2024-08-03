package com.openweather.core.data.di

import android.content.Context
import androidx.work.WorkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object WorkManagerModule {

    @Provides
    fun workManager(
        @ApplicationContext context: Context
    ): WorkManager = WorkManager.getInstance(context)
}