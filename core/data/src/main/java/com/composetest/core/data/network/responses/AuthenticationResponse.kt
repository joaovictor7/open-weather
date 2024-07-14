package com.composetest.core.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val token: String,
    val authenticationDate: String,
    val user: UserResponse
)
