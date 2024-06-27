package com.composetest.ui

import androidx.lifecycle.viewModelScope
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.analytics.AnalyticsUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeFromDataStoreUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import com.composetest.ui.analytics.MainAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeFromDataStoreUseCase: GetAppThemeFromDataStoreUseCase,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    override val analyticsUseCase: AnalyticsUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainUiState>(MainAnalytic(), MainUiState()) {

    init {
        getAppThemeFromDataStore()
        iniState()
        getInitialData()
    }

    private fun getAppThemeFromDataStore() = viewModelScope.launch(dispatcher) {
        getAppThemeFromDataStoreUseCase()
    }

    private fun iniState() = safeRunFlowTask(flowTask = getAppThemeStateUseCase()) {
        setSystemStyles(it)
    }

    private fun getInitialData() = viewModelScope.launch(dispatcher) {
        updateUiState { it.finishSplashScreen() }
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateUiState {
            it.setAppTheme(appThemeModel)
        }
    }
}