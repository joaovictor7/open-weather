package com.composetest.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeFromDataStoreUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import com.composetest.core.domain.usecases.session.CheckSessionEndUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeFromDataStoreUseCase: GetAppThemeFromDataStoreUseCase,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    private val checkSessionEndUseCase: CheckSessionEndUseCase,
    private val navigationProvider: NavigationProvider,
    override val analyticsUseCase: AnalyticsUseCase
) : BaseViewModel<MainUiState>(MainAnalytic(), MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        getAppThemeFromDataStore()
        iniState()
        getInitialData()
    }

    override fun verifySession() {
        runAsyncTask {
            val validSession = checkSessionEndUseCase()
            val currentScreenIsLogin = navigationProvider.currentDestinationCheck(LoginDestination)
            if (!validSession && !currentScreenIsLogin) {
                navigationProvider.navigate(LoginDestination, NavigationMode.REMOVE_ALL_SCREENS)
            }
        }
    }

    private fun getAppThemeFromDataStore() = runAsyncTask {
        getAppThemeFromDataStoreUseCase()
    }

    private fun iniState() = runFlowTask(flow = getAppThemeStateUseCase()) {
        setSystemStyles(it)
    }

    private fun getInitialData() {
        updateUiState { it.splashScreenFinished() }
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateUiState { it.setAppTheme(appThemeModel) }
    }
}