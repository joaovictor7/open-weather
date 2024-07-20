package com.composetest.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeUseCase
import com.composetest.core.domain.usecases.session.CheckSessionEndUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    private val checkSessionEndUseCase: CheckSessionEndUseCase,
    private val navigationProvider: NavigationProvider,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<MainUiState>(MainAnalytic(), MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        iniState()
        getInitialData()
    }

    override fun verifySession() {
        runAsyncTask {
            val validSession = checkSessionEndUseCase()
            val currentScreenIsLogin = navigationProvider.currentDestinationCheck(LoginDestination)
            if (!validSession && !currentScreenIsLogin) {
                navigationProvider.navigate(LoginDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
            }
        }
    }

    private fun iniState() {
        runFlowTask(flow = getAppThemeUseCase()) { appTheme ->
            updateUiState { it.setAppTheme(appTheme) }
        }
    }

    private fun getInitialData() {
        updateUiState { it.splashScreenFinished() }
    }
}