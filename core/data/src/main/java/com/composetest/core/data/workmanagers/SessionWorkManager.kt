package com.composetest.core.data.workmanagers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.composetest.core.data.domain.enums.WorkManagerName
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.time.Duration

@HiltWorker
class SessionWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("teste", "portugal")
        return Result.success()
    }

    class Builder(
        initialDelay: Duration
    ) : WorkManager.OneTimeWorkManager {
        override val work = WorkManagerName.SESSION
        override val existingWorkPolicy = ExistingWorkPolicy.REPLACE
        override val request = OneTimeWorkRequestBuilder<SessionWorkManager>()
            .addTag(WorkManagerName.SESSION.name)
            .setConstraints(Constraints(requiredNetworkType = NetworkType.CONNECTED))
            .setInitialDelay(initialDelay)
            .build()
    }
}