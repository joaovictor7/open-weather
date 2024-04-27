package com.composetest.feature.login.ui

import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
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
) : BaseViewModel<LoginAction, LoginState>(LoginState()) {

    private var loginModel: LoginModel? = null
    private val buildConfigModel get() = buildConfigProvider.buildConfigModel

    init {
        initState()
    }

    override fun handleAction(action: LoginAction) = when (action) {
        is LoginAction.CheckEmail -> checkEmail()
        is LoginAction.Login -> login()
        is LoginAction.WriteData -> writeData(action)
    }

    private fun checkEmail() {
        loginModel?.let { loginModel ->
            stateValue = stateValue.setInvalidEmail(!EMAIL_ADDRESS.matcher(loginModel.email).matches())
        }
    }

    private fun login() {
        loginModel?.let {
            asyncFlowTask(
                flowTask = loginUseCase.login(it),
                onSuccessTask = ::processLoginResponse,
                onErrorTask = ::errorLogin
            )
        }
    }

    private fun writeData(data: LoginAction.WriteData) {
        loginModel = LoginModel(data.email, data.password)
        if (stateValue.invalidEmail)
            stateValue = stateValue.setInvalidEmail(false)
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