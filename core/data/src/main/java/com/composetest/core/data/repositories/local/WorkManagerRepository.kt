package com.composetest.core.data.repositories.local

import com.composetest.core.data.workmanagers.WorkManager as Work

interface WorkManagerRepository {
    fun createPeriodicWork(workManager: Work.PeriodicWorkManager)
    fun createOneTimeWork(workManager: Work.OneTimeWorkManager)
}