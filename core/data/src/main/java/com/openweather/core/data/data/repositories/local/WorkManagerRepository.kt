package com.openweather.core.data.data.repositories.local

import com.openweather.core.data.workmanagers.WorkManager as Work

interface WorkManagerRepository {
    fun createPeriodicWork(workManager: Work.PeriodicWorkManager)
    fun createOneTimeWork(workManager: Work.OneTimeWorkManager)
}