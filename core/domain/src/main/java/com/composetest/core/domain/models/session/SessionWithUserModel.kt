package com.composetest.core.domain.models.session

import com.composetest.core.domain.models.UserModel
import java.time.LocalDateTime

data class SessionWithUserModel(
    val id: Long = 0,
    val token: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isFinished: Boolean,
    val user: UserModel
)