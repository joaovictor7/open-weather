package com.composetest.feature.login.ui.login

import com.composetest.common.bases.BaseViewModel
import com.composetest.common.enums.Theme
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.throwables.InvalidCredentialsThrowable
import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog.Companion.getErrorAlertDialogType
import com.composetest.feature.login.models.LoginFormModel
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.apptheme.GetCurrentAppThemeUseCase
import com.composetest.core.domain.usecases.apptheme.SetCustomThemeUseCase
import com.composetest.core.router.navigation.home.HomeDestination
import com.composetest.core.router.navigation.home.navtypes.InnerHome
import com.composetest.common.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val getCurrentAppThemeUseCase: GetCurrentAppThemeUseCase,
    private val setCustomThemeUseCase: SetCustomThemeUseCase,
    private val authenticationUseCase: AuthenticationUseCase
) : BaseViewModel<LoginEvent, LoginState>(LoginState()) {

    private var loginFormModel: LoginFormModel = LoginFormModel()

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
        if (loginFormModel.emailIsEmpty) {
            updateState { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    private fun login() {
        asyncFlowTask(
            flowTask = authenticationUseCase(loginFormModel.email, loginFormModel.password),
            onStart = { updateState { it.setLoading(true) } },
            onCompletion = { updateState { it.setLoading(false) } },
            onError = ::handleLoginError,
            onCollect = { handleLoginSuccess() }
        )
    }

    private fun writeData(action: LoginEvent.WriteData) {
        when {
            action.email != null -> {
                loginFormModel = loginFormModel.copy(email = action.email)
                if (state.value.invalidEmail) {
                    updateState { it.setInvalidEmail(false) }
                }
            }
            action.password != null -> {
                loginFormModel = loginFormModel.copy(password = action.password)
            }
        }
        resetViewState()
    }

    private fun resetViewState() {
        updateState {
            it.resetStateView(loginFormModel.loginAlready || buildConfigProvider.get.isDebug)
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
        val theme = if (event.enterScreen && getCurrentAppThemeUseCase().theme != Theme.DARK)
            Theme.DARK
        else null
        setCustomThemeUseCase(theme)
    }

    private fun handleLoginSuccess() {
        navigationProvider.navigate(HomeDestination("teste", InnerHome("te", "23232")))
    }

    private fun handleLoginError(error: Throwable?) {
        updateState {
            if (error is InvalidCredentialsThrowable) {
                it.setShowInvalidCredentialsMsg()
            } else {
                it.setAlertDialogError(error.getErrorAlertDialogType())
            }
        }
    }
}