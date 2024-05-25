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
    val isLoading: Boolean = false,
    override val errorAlertDialogType: ErrorAlertDialogType = ErrorAlertDialogType.NONE
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
    fun resetStateView(enableLoginButton: Boolean) = copy(
        enableLoginButton = enableLoginButton,
        invalidCredentials = false,
    )
    fun setShowInvalidCredentialsMsg() = copy(invalidCredentials = true)
    fun setLoading(isLoading: Boolean) = copy(
        isLoading = isLoading,
        invalidCredentials = false
    )
    fun setAlertDialogError(alertErrorDialogType: ErrorAlertDialogType) = copy(
        errorAlertDialogType = alertErrorDialogType
    )
}