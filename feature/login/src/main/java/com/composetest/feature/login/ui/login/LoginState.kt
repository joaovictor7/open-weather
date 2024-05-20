package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.domain.models.AppThemeModel

data class LoginState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val loginError: Boolean = false
) {
    fun initState(
        versionName: String,
        enableLoginButton: Boolean
    ) = copy(
        versionName = versionName,
        enableLoginButton = enableLoginButton
    )
    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)
    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)
    fun setEnableLoginButton(enable: Boolean) = copy(enableLoginButton = enable)
    fun setError() = copy(loginError = true)
}