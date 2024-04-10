package com.composetest.feature.login.ui

import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.models.LoginModel
import com.composetest.router.destinations.HomeDestinations
import com.composetest.router.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val homeDestination: HomeDestinations.Home,
    private val buildConfigProvider: BuildConfigProvider
) : BaseViewModel<LoginAction, LoginState>(LoginState()) {

    private var loginModel: LoginModel? = null

    init {
        init()
        val y = ""
    }

    override fun handleAction(action: LoginAction) = when(action) {
        is LoginAction.ClickEnter -> clickEnter()
        is LoginAction.WriteData -> writeData(action)
    }

    private fun clickEnter() {
        navigationProvider.navigateWithArgs(homeDestination, HomeParam("teste"))
    }

    private fun writeData(data: LoginAction.WriteData) {
        loginModel = LoginModel(data.email, data.password)
    }

    private fun init() = _state.update {
        it.setVersionName(buildConfigProvider.buildConfigModel.versionNameWithVersionCode)
    }
}