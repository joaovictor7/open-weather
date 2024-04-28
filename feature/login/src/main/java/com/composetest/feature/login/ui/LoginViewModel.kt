package com.composetest.feature.login.ui

import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.domain.models.LoginModel
import com.composetest.feature.login.data.usecases.LoginUseCase
import com.composetest.router.domain.enums.Destination
import com.composetest.router.domain.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginEvent, LoginState>(LoginState()) {

    private var loginModel: LoginModel = LoginModel()
    private val buildConfigModel get() = buildConfigProvider.buildConfigModel

    init {
        initState()
    }

    override fun handleEvent(event: LoginEvent) = when (event) {
        is LoginEvent.CheckShowInvalidEmailMsg -> showInvalidEmailMsg()
        is LoginEvent.Login -> login()
        is LoginEvent.WriteData -> writeData(event)
    }

    private fun showInvalidEmailMsg() {
        if (loginModel.emailIsEmpty) {
            stateValue = stateValue.setInvalidEmail(!loginModel.emailIsValid)
        }
    }

    private fun login() {
        asyncFlowTask(
            flowTask = loginUseCase.login(loginModel),
            onSuccessTask = ::processLoginResponse,
            onErrorTask = ::errorLogin
        )
    }

    private fun writeData(action: LoginEvent.WriteData) {
        when {
            action.email != null -> {
                loginModel = loginModel.copy(email = action.email)
                if (stateValue.invalidEmail) {
                    stateValue = stateValue.setInvalidEmail(false)
                }
            }
            action.password != null -> {
                loginModel = loginModel.copy(password = action.password)
            }
        }
        loginButtonManager()
    }

    private fun loginButtonManager() {
        stateValue = stateValue.setEnableLoginButton(loginModel.loginAlready)
    }

    private fun initState() {
        stateValue = stateValue.setVersionName(buildConfigModel.versionNameForView)
    }

    private fun processLoginResponse(success: Boolean) {
        if (success) {
            navigationProvider.navigateWithArgs(Destination.FEATURE_HOME, HomeParam("teste"))
        }
    }

    private fun errorLogin(error: Throwable) {
        stateValue = stateValue.setError()
    }
}