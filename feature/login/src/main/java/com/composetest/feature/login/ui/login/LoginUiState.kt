package com.composetest.feature.login.ui.login

import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog
import com.composetest.common.models.AppThemeModel

data class LoginUiState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val invalidCredentials: Boolean = false,
    val isLoading: Boolean = false,
    val errorAlertDialog: ErrorAlertDialog = ErrorAlertDialog.NONE
) {
    fun initState(
        versionName: String,
        enableLoginButton: Boolean
    ) = copy(
        versionName = versionName,
        enableLoginButton = enableLoginButton
    )
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
    fun setAlertDialogError(alertErrorDialogType: ErrorAlertDialog) = copy(
        errorAlertDialog = alertErrorDialogType
    )
}