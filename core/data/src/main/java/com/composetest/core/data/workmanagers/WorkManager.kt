package com.composetest.core.data.workmanagers

import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import com.composetest.core.data.enums.WorkManagerName

sealed interface WorkManager {
    val work: WorkManagerName

    interface OneTimeWorkManager : WorkManager {
        val workRequest: OneTimeWorkRequest
        val existingWorkPolicy: ExistingWorkPolicy
    }

    interface PeriodicWorkManager : WorkManager {
        val workRequest: PeriodicWorkRequest
        val existingPeriodicWorkPolicy: ExistingPeriodicWorkPolicy
    }
}

