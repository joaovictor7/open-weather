package com.composetest.core.test.models

import kotlinx.coroutines.Job

data class ObserverStateFlowModel <T>(
    val job: Job,
    val collectedStates: List<T>
) {
    fun cancelJob() {
        job.cancel()
    }
}
