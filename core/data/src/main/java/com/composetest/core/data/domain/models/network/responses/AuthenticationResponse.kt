package com.composetest.core.data.domain.models.network.responses

data class AuthenticationResponse(
    val id: String = String(),
    val email: String = String(),
    val name: String? = null
)
