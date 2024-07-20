package com.composetest.feature.login.ui.login

import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam
import com.composetest.core.designsystem.components.textfields.enums.TextFieldIcons
import com.composetest.core.designsystem.components.textfields.params.TextFieldTrailingIconParam
import com.composetest.core.ui.interfaces.BaseUiState
import com.composetest.feature.login.R
import com.composetest.feature.login.models.LoginFormModel

internal data class LoginUiState(
    val loginFormModel: LoginFormModel = LoginFormModel(),
    val needsLogin: Boolean = false,
    val versionName: String = String(),
    val invalidEmail: Boolean = false,
    val enableLoginButton: Boolean = false,
    val invalidCredentials: Boolean = false,
    val errorAlertDialogParam: ErrorAlertDialogParam? = null,
    override var isLoading: Boolean = false
) : BaseUiState {
    val emailTrailingIconTextField get() = if (invalidEmail)
        TextFieldTrailingIconParam(iconType = TextFieldIcons.ERROR) else null
    val emailSupportingTextField get() = if (invalidEmail)
        R.string.feature_login_invalid_email else null

    fun initState(
        versionName: String,
        enableLoginButton: Boolean
    ) = copy(
        needsLogin = true,
        versionName = versionName,
        enableLoginButton = enableLoginButton
    )
    fun setLoginForm(email: String?, password: String?) = when {
        email != null -> {
            copy(invalidEmail = false, loginFormModel = loginFormModel.copy(email = email))
        }
        password != null -> copy(loginFormModel = loginFormModel.copy(password = password))
        else -> this
    }
    fun setInvalidEmail(invalid: Boolean) = copy(invalidEmail = invalid)
    fun setEnabledButton(enable: Boolean) = copy(enableLoginButton = enable)
    fun setShowInvalidCredentialsMsg(show: Boolean) = copy(invalidCredentials = show)
    fun setLoading(isLoading: Boolean) = copy(isLoading = isLoading)
    fun setAlertDialogError(errorAlertDialogParam: ErrorAlertDialogParam?) = copy(
        errorAlertDialogParam = errorAlertDialogParam
    )
}