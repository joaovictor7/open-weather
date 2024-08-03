package com.openweather.core.domain.models.session

import java.time.LocalDateTime

data class SessionModel(
    val id: Long,
    val token: String,
    val initialDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isFinished: Boolean
)
