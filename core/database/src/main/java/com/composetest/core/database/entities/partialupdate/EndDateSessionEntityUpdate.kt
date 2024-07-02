package com.composetest.core.database.entities.partialupdate

import java.time.LocalDateTime

data class EndDateSessionEntityUpdate(
    val sessionId: Long,
    val endDate: LocalDateTime
)