package com.composetest.feature.login.ui

import androidx.navigation.NavHostController
import com.composetest.core.factories.ViewModelNavigationFactory
import com.composetest.core.providers.BuildConfigProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.models.LoginModel
import com.composetest.router.destinations.HomeDestinations
import com.composetest.router.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = LoginViewModel.Factory::class)
class LoginViewModel @AssistedInject constructor(
    @Assisted private val navController: NavHostController,
    private val navigationProvider: NavigationProvider,
    private val homeDestination: HomeDestinations.Home,
    private val buildConfigProvider: BuildConfigProvider
) : BaseViewModel<LoginAction, LoginState>(LoginState()) {

    private var loginModel: LoginModel? = null

    init {
        init()
    }

    override fun handleAction(action: LoginAction) = when(action) {
        is LoginAction.ClickEnter -> clickEnter()
        is LoginAction.WriteData -> writeData(action)
    }

    private fun clickEnter() {
        navigationProvider.navigateWithArgs(navController, homeDestination, HomeParam("teste"))
    }

    private fun writeData(data: LoginAction.WriteData) {
        loginModel = LoginModel(data.email, data.password)
    }

    private fun init() = _state.update {
        it.setVersionName(buildConfigProvider.buildConfigModel.versionNameWithVersionCode)
    }

    @AssistedFactory
    sealed interface Factory : ViewModelNavigationFactory<LoginViewModel>
}