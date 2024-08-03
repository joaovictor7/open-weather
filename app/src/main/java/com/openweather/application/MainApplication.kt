package com.openweather.application

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.openweather.core.data.di.entrypoints.WorkerFactoryEntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltAndroidApp
internal class MainApplication : Application(), Configuration.Provider {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    override val workManagerConfiguration = Configuration.Builder()
        .setWorkerFactory(
            EntryPoints.get(this, WorkerFactoryEntryPoint::class.java).workerFactory()
        )
        .build()
}