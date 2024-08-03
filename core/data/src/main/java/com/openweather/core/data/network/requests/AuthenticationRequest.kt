package com.openweather.core.data.network.requests

import kotlinx.serialization.Serializable

@Serializable
data class AuthenticationRequest(
    val email: String,
    val password: String
)
