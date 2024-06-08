package com.composetest.core.domain.models

import java.time.LocalDateTime

data class SessionWithUserModel(
    val id: Long = 0,
    val token: String,
    val initialDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val user: UserModel
)