package com.composetest.core.data.providers

import android.content.Context
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import com.composetest.core.data.workmanagers.WorkManager as Work

@Singleton
internal class WorkManagerProviderImpl @Inject constructor(
    @ApplicationContext context: Context
) : WorkManagerProvider {

    private val workManager = WorkManager.getInstance(context)

    override fun createPeriodicWork(workManager: Work.PeriodicWorkManager) {
        this.workManager.enqueueUniquePeriodicWork(
            workManager.work.name,
            workManager.existingPeriodicWorkPolicy,
            workManager.request
        )
    }

    override fun createOneTimeWork(workManager: Work.OneTimeWorkManager) {
        this.workManager.enqueueUniqueWork(
            workManager.work.name,
            workManager.existingWorkPolicy,
            workManager.request
        )
    }
}