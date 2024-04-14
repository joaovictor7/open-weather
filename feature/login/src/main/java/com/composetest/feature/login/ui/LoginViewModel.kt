package com.composetest.feature.login.ui

import com.composetest.core.managers.BuildConfigManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.models.LoginModel
import com.composetest.feature.login.infra.usecases.LoginUseCase
import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import com.composetest.router.params.home.HomeParam
import com.composetest.router.managers.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @Destinations(Destination.HOME) private val homeDestination: ScreenDestination,
    private val navigationManager: NavigationManager,
    private val buildConfigManager: BuildConfigManager,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginAction, LoginState>(LoginState()) {

    private var loginModel: LoginModel? = null

    init {
        init()
    }

    override fun handleAction(action: LoginAction) = when (action) {
        is LoginAction.ClickEnter -> clickEnter()
        is LoginAction.WriteData -> writeData(action)
    }

    private fun clickEnter() {
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
    }

    private fun init() = _state.update {
        it.setVersionName(buildConfigManager.buildConfigModel.versionNameWithVersionCode)
    }

    private fun processLoginResponse(success: Boolean) {
        if (success) {
            navigationManager.navigateWithArgs(homeDestination, HomeParam("teste"))
        }
    }

    private fun errorLogin(error: Throwable) = _state.update {
        it.setError()
    }
}