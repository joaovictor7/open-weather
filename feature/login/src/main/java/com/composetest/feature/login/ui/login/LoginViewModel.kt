package com.composetest.feature.login.ui.login

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.enums.Theme
import com.composetest.common.providers.BuildConfigProvider
import com.composetest.common.throwables.InvalidCredentialsThrowable
import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog.Companion.getErrorAlertDialogType
import com.composetest.feature.login.models.LoginFormModel
import com.composetest.core.domain.usecases.AuthenticationUseCase
import com.composetest.core.domain.usecases.analytics.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import com.composetest.core.domain.usecases.apptheme.SetAppThemeUseCase
import com.composetest.core.router.destinations.home.HomeDestination
import com.composetest.core.router.destinations.home.navtypes.InnerHome
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.feature.login.ui.login.analytics.LoginAnalytic
import com.composetest.feature.login.ui.login.analytics.LoginClickEventAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val authenticationUseCase: AuthenticationUseCase,
    override val analyticsUseCase: AnalyticsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher,
) : BaseViewModel<LoginUiState>(LoginAnalytic(), LoginUiState()), LoginCommandReceiver {

    private var loginFormModel: LoginFormModel = LoginFormModel()

    init {
        openScreenAnalytic()
        initState()
    }

    override fun checkShowInvalidEmailMsg() {
        if (loginFormModel.emailIsEmpty) {
            updateUiState { it.setInvalidEmail(!loginFormModel.emailIsValid) }
        }
    }

    override fun login() {
        safeRunFlowTask(
            flowTask = authenticationUseCase(loginFormModel.email, loginFormModel.password),
            onStart = {
                analyticsUseCase(LoginClickEventAnalytic())
                updateUiState { it.setLoading(true) }
            },
            onCompletion = { updateUiState { it.setLoading(false) } },
            onError = ::handleLoginError,
            onCollect = { handleLoginSuccess() }
        )
    }

    override fun writeData(email: String?, password: String?) {
        when {
            email != null -> {
                loginFormModel = loginFormModel.copy(email = email)
                if (uiState.value.invalidEmail) {
                    updateUiState { it.setInvalidEmail(false) }
                }
            }
            password != null -> {
                loginFormModel = loginFormModel.copy(password = password)
            }
        }
        resetViewState()
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
                it.setShowInvalidCredentialsMsg()
            } else {
                it.setAlertDialogError(throwable.getErrorAlertDialogType())
            }
        }
    }

    private fun handleLoginSuccess() {
        navigationProvider.navigate(
            HomeDestination("teste", InnerHome("te", "23232"))
        )
    }

    private fun initState() {
        updateUiState {
            it.initState(
                versionName = buildConfigProvider.get.versionNameForView,
                enableLoginButton = buildConfigProvider.get.isDebug
            )
        }
    }

    private fun resetViewState() {
        updateUiState {
            it.resetStateView(loginFormModel.loginAlready || buildConfigProvider.get.isDebug)
        }
    }
}