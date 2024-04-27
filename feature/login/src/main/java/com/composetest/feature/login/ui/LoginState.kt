package com.composetest.feature.login.ui

data class LoginState(
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val loginError: Boolean = false
) {
    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)
    fun setVersionName(versionName: String) = copy(versionName = versionName)
    fun setError() = copy(loginError = true)
}