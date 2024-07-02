package com.composetest.core.domain.models.session

import com.composetest.core.domain.models.UserModel
import java.time.LocalDateTime

data class SessionWithUserModel(
    val id: Long = 0,
    val token: String,
    val initialDate: LocalDateTime,
    val endDate: LocalDateTime? = null,
    val user: UserModel
)