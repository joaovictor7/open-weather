package com.composetest.feature.login.ui

data class LoginState(
    val versionName: String = String(),
    val showPassword: Boolean = false,
    val invalidEmail: Boolean = false,
    val loginError: Boolean = false
) {
    fun setVersionName(versionName: String) = copy(versionName = versionName)
}