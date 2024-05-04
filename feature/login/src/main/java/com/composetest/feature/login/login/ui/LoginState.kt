package com.composetest.feature.login.login.ui

internal data class LoginState(
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val loginError: Boolean = false
) {
    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)
    fun setEnableLoginButton(enable: Boolean) = copy(enableLoginButton = enable)
    fun setVersionName(versionName: String) = copy(versionName = versionName)
    fun setError() = copy(loginError = true)
}