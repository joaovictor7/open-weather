package com.composetest.core.data.domain.models.network.responses

data class AuthenticationResponse(
    val token: String = String(),
    val authenticationDate: Long,
    val user: UserResponse = UserResponse()
)
