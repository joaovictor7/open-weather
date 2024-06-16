package com.composetest.ui

import androidx.lifecycle.viewModelScope
import com.composetest.common.abstracts.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.apptheme.GetAppThemeFromDataStoreUseCase
import com.composetest.core.domain.usecases.apptheme.GetAppThemeStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeFromDataStoreUseCase: GetAppThemeFromDataStoreUseCase,
    private val getAppThemeStateUseCase: GetAppThemeStateUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : BaseViewModel<MainUiState>(MainUiState()) {

    init {
        getAppThemeFromDataStore()
        iniState()
    }

    private fun getAppThemeFromDataStore() = viewModelScope.launch(dispatcher) {
        getAppThemeFromDataStoreUseCase()
    }

    private fun iniState() {
        asyncFlowTask(flowTask = getAppThemeStateUseCase()) {
            setSystemStyles(it)
        }
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateUiState {
            it.setAppTheme(appThemeModel)
        }
    }
}