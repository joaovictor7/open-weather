package com.composetest.core.data.domain.models.network.requests

data class AuthenticationRequest(
    val login: String,
    val password: String
)
