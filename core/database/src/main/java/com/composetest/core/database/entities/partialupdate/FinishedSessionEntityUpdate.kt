package com.composetest.core.database.entities.partialupdate

data class FinishedSessionEntityUpdate(
    val sessionId: Long,
    val isFinished: Boolean
)