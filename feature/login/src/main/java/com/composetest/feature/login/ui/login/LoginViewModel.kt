package com.composetest.feature.login.ui.login

import com.composetest.common.enums.Theme
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.core.designsystem.components.alertdialogs.extensions.errorAlertDialogParam
import com.composetest.core.domain.throwables.InvalidCredentialsThrowable
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import com.composetest.core.domain.usecases.apptheme.SetAppThemeUseCase
import com.composetest.core.domain.usecases.session.GetNeedsLoginBySessionUseCase
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.home.navtypes.InnerHome
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.login.ui.login.analytics.LoginAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    private val getNeedsLoginBySessionUseCase: GetNeedsLoginBySessionUseCase,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<LoginUiState>(LoginAnalytic(), LoginUiState()), LoginCommandReceiver {

    override val commandReceiver = this

    private val loginFormModel get() = uiState.value.loginFormModel

    init {
        checkNeedsLogin()
    }

    override fun checkShowInvalidEmailMsg() {
        if (loginFormModel.emailIsNotEmpty) {
            updateUiState { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    override fun login() {
        runAsyncTask(
            onError = ::handleLoginError,
            onCompletion = { updateUiState { it.setLoading(false) } },
            onStart = {
                updateUiState { it.setLoading(true).setShowInvalidCredentialsMsg(false) }
            }
        ) {
            authenticationUseCase(loginFormModel.email, loginFormModel.password)
            navigateToHome()
        }
    }

    override fun writeData(email: String?, password: String?) {
        updateUiState { it.setLoginForm(email, password) }
        stateScreenWritingManager()
    }

    override fun setCustomTheme(enterScreen: Boolean) {
        val theme = if (enterScreen && getAppThemeStateUseCase().value.theme != Theme.DARK)
            Theme.DARK
        else null
        setAppThemeUseCase.invoke(theme)
    }

    override fun handleLoginError(throwable: Throwable?) {
        updateUiState {
            if (throwable is InvalidCredentialsThrowable) {
                it.setShowInvalidCredentialsMsg(true)
            } else {
                it.setAlertDialogError(throwable.errorAlertDialogParam)
            }
        }
    }

    private fun checkNeedsLogin() = runAsyncTask {
        if (getNeedsLoginBySessionUseCase()) {
            openScreenAnalytic()
            initState()
        } else {
            navigateToHome()
        }
    }

    private fun initState() {
        updateUiState {
            it.initState(
                versionName = buildConfigProvider.get.versionNameForView,
                enableLoginButton = buildConfigProvider.get.isDebug
            )
        }
    }

    private suspend fun navigateToHome() {
        navigationProvider.asyncNavigate(
            HomeDestination("teste", InnerHome("te", "23232")),
            NavigationMode.REMOVE_ALL_SCREENS
        )
    }

    private fun stateScreenWritingManager() {
        updateUiState {
            it.setShowInvalidCredentialsMsg(false)
                .setEnabledButton(loginFormModel.loginAlready || buildConfigProvider.get.isDebug)
        }
    }
}