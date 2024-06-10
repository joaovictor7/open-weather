package com.composetest.core.data.repositories

import androidx.work.WorkManager
import javax.inject.Inject
import javax.inject.Singleton
import com.composetest.core.data.workmanagers.WorkManager as Work

@Singleton
internal class WorkManagerRepositoryImpl @Inject constructor(
    private val workManager: WorkManager
) : WorkManagerRepository {

    override fun createPeriodicWork(workManager: Work.PeriodicWorkManager) {
        this.workManager.enqueueUniquePeriodicWork(
            workManager.work.name,
            workManager.existingPeriodicWorkPolicy,
            workManager.workRequest
        )
    }

    override fun createOneTimeWork(workManager: Work.OneTimeWorkManager) {
        this.workManager.enqueueUniqueWork(
            workManager.work.name,
            workManager.existingWorkPolicy,
            workManager.workRequest
        )
    }
}