package com.composetest.core.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val token: String,
    val sessionStartDate: String,
    val sessionEndDate: String,
    val user: UserResponse
)
