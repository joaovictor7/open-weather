package com.openweather.core.database.entities.partialupdate

data class FinishedSessionEntityUpdate(
    val sessionId: Long,
    val isFinished: Boolean
)