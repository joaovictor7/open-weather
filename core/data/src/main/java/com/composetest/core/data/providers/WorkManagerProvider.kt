package com.composetest.core.data.providers

import com.composetest.core.data.workmanagers.WorkManager as Work

interface WorkManagerProvider {
    fun createPeriodicWork(workManager: Work.PeriodicWorkManager)
    fun createOneTimeWork(workManager: Work.OneTimeWorkManager)
}