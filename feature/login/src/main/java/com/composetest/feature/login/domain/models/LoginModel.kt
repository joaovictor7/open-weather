package com.composetest.feature.login.domain.models

import androidx.core.util.PatternsCompat

internal data class LoginModel(
    val email: String = String(),
    val password: String = String()
) {
    val emailIsValid get() = PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    val emailIsEmpty get() = email.isNotEmpty()
    val loginAlready get() = email.isNotEmpty() and password.isNotEmpty() and emailIsValid
}
