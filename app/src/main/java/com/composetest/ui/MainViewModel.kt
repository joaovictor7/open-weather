package com.composetest.ui

import androidx.lifecycle.viewModelScope
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeFromDataStoreUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import com.composetest.core.domain.usecases.session.CheckSessionEndUseCase
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.providers.NavigationProvider
import com.composetest.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeFromDataStoreUseCase: GetAppThemeFromDataStoreUseCase,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    private val checkSessionEndUseCase: CheckSessionEndUseCase,
    private val navigationProvider: NavigationProvider,
    override val analyticsUseCase: AnalyticsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainUiState>(MainAnalytic(), MainUiState()), MainCommandReceiver {

    override val commandReceiver = this

    init {
        getAppThemeFromDataStore()
        iniState()
        getInitialData()
    }

    override fun verifySession() {
        viewModelScope.launch(dispatcher) {
            val validSession = checkSessionEndUseCase()
            val currentScreenIsLogin = navigationProvider.currentDestinationCheck(LoginDestination)
            if (!validSession && !currentScreenIsLogin) {
                navigationProvider.asyncNavigateRemovePrevious(LoginDestination)
            }
        }
    }

    private fun getAppThemeFromDataStore() = viewModelScope.launch(dispatcher) {
        getAppThemeFromDataStoreUseCase()
    }

    private fun iniState() = collectFlow(flow = getAppThemeStateUseCase()) {
        setSystemStyles(it)
    }

    private fun getInitialData() {
        updateUiState { it.splashScreenFinished() }
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateUiState { it.setAppTheme(appThemeModel) }
    }
}