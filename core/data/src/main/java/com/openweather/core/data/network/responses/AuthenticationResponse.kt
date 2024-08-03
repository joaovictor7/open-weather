package com.openweather.core.data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationResponse(
    val user: UserResponse,
    val sessionResponse: SessionResponse
)
