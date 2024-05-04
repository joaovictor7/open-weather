package com.composetest.feature.login.login.ui

import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.login.domain.models.LoginModel
import com.composetest.feature.login.login.domain.usecases.LoginUseCase
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.navigation.home.InnerHome
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
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
        stateValue = stateValue.setEnableLoginButton(loginModel.loginAlready || buildConfigModel.useMock)
    }

    private fun initState() {
        stateValue = stateValue
            .setVersionName(buildConfigModel.versionNameForView)
            .setEnableLoginButton(buildConfigModel.useMock)
    }

    private fun processLoginResponse(success: Boolean) {
        if (success) {
            navigationProvider.navigate(HomeDestination("teste", InnerHome("te","23232")))
        }
    }

    private fun errorLogin(error: Throwable) {
        stateValue = stateValue.setError()
    }
}