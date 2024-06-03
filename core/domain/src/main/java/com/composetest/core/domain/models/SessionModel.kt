package com.composetest.core.domain.models

import java.time.LocalDateTime

data class SessionModel(
    val initialDate: LocalDateTime,
    val user: UserModel,
    val endDate: LocalDateTime? = null
)