package com.openweather.ui

import com.openweather.core.domain.managers.AppThemeManager
import com.openweather.core.domain.managers.SessionManager
import com.openweather.core.ui.bases.BaseViewModel
import com.openweather.core.domain.usecases.AnalyticsUseCase
import com.openweather.core.router.destinations.login.LoginDestination
import com.openweather.core.router.enums.NavigationMode
import com.openweather.core.router.providers.NavHostControllerProvider
import com.openweather.core.router.managers.NavigationManager
import com.openweather.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appThemeManager: AppThemeManager,
    private val sessionManager: SessionManager,
    private val navigationManager: NavigationManager,
    private val navHostControllerProvider: NavHostControllerProvider,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<MainUiState>(MainAnalytic, MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        iniState()
        getInitialData()
    }

    override fun verifySession() {
        runAsyncTask {
            val validSession = sessionManager.isSessionValid()
            val currentScreenIsLogin = navHostControllerProvider.isCurrentScreen(LoginDestination)
            if (!validSession && !currentScreenIsLogin) {
                navigationManager.navigate(LoginDestination, NavigationMode.REMOVE_ALL_SCREENS_STACK)
            }
        }
    }

    private fun iniState() {
        runFlowTask(flow = appThemeManager.getAppTheme()) { appTheme ->
            updateUiState { it.setAppTheme(appTheme) }
        }
    }

    private fun getInitialData() {
        updateUiState { it.splashScreenFinished() }
    }
}