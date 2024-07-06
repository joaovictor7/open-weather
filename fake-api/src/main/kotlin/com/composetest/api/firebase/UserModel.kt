package com.composetest.api.firebase

import io.ktor.server.auth.Principal

data class UserModel(
    val userId: String,
    val name: String?,
    val email: String
) : Principal
