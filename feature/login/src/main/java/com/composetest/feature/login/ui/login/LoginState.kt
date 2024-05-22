package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.domain.bases.BaseState
import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType
import com.composetest.core.designsystem.domain.models.AppThemeModel

data class LoginState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val invalidCredentials: Boolean = false,
    override val alertErrorDialogType: ErrorAlertDialogType = ErrorAlertDialogType.NONE
): BaseState {
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
    fun setInvalidCredentialError(error: Boolean) = copy(invalidCredentials = error)
    fun setAlertDialogError(alertErrorDialogType: ErrorAlertDialogType) = copy(
        alertErrorDialogType = alertErrorDialogType
    )
}