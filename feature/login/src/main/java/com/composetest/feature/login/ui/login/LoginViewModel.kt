package com.composetest.feature.login.ui.login

import com.composetest.core.utility.providers.BuildConfigProvider
import com.composetest.core.designsystem.domain.bases.BaseViewModel
import com.composetest.core.designsystem.domain.emuns.AppTheme
import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType.Companion.getErrorAlertDialogType
import com.composetest.core.designsystem.providers.AppThemeProvider
import com.composetest.feature.login.domain.models.LoginModel
import com.composetest.feature.login.domain.usecases.LoginUseCase
import com.composetest.core.router.navigation.home.HomeDestination
import com.composetest.core.router.navigation.home.navtypes.InnerHome
import com.composetest.core.router.providers.NavigationProvider
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val appThemeProvider: AppThemeProvider,
    private val navigationProvider: NavigationProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginEvent, LoginState>(LoginState()) {

    private var loginModel: LoginModel = LoginModel()
    private val currentAppTheme get() = appThemeProvider.currentAppTheme

    init {
        initState()
    }

    override fun handleEvent(event: LoginEvent) = when (event) {
        is LoginEvent.SetCustomTheme -> setCustomTheme(event)
        is LoginEvent.CheckShowInvalidEmailMsg -> showInvalidEmailMsg()
        is LoginEvent.Login -> login()
        is LoginEvent.WriteData -> writeData(event)
        is LoginEvent.DismissErrorAlertDialog -> handleLoginError(null)
    }

    private fun showInvalidEmailMsg() {
        if (loginModel.emailIsEmpty) {
            updateState { it.setInvalidEmail(!loginModel.emailIsValid) }
        }
    }

    private fun login() {
        lazyFlowTask(
            flowTask = loginUseCase.login(loginModel),
            onSuccess = ::handleLoginSuccess,
            onError = ::handleLoginError
        )
    }

    private fun writeData(action: LoginEvent.WriteData) {
        when {
            action.email != null -> {
                loginModel = loginModel.copy(email = action.email)
                if (state.value.invalidEmail) {
                    updateState { it.setInvalidEmail(false) }
                }
            }
            action.password != null -> {
                loginModel = loginModel.copy(password = action.password)
            }
        }
        resetViewState()
    }

    private fun resetViewState() {
        updateState {
            it.setEnableLoginButton(loginModel.loginAlready || buildConfigProvider.get.isDebug)
                .setInvalidCredentialError(false)
        }
    }

    private fun initState() {
        updateState {
            it.initState(
                versionName = buildConfigProvider.get.versionNameForView,
                enableLoginButton = buildConfigProvider.get.isDebug
            )
        }
    }

    private fun setCustomTheme(event: LoginEvent.SetCustomTheme) {
        appThemeProvider.setCustomTheme(
            if (event.enterScreen && currentAppTheme.theme != AppTheme.DARK) {
                AppTheme.DARK
            } else {
                null
            }
        )
        updateState { it.setAppTheme(currentAppTheme) }
    }

    private fun handleLoginSuccess(success: Boolean) {
        if (success) {
            navigationProvider.navigate(HomeDestination("teste", InnerHome("te", "23232")))
        }
    }

    private fun handleLoginError(error: Throwable?) {
        updateState {
            if (error is FirebaseAuthInvalidCredentialsException) {
                it.setInvalidCredentialError(true)
            } else {
                it.setAlertDialogError(error.getErrorAlertDialogType())
            }
        }
    }
}