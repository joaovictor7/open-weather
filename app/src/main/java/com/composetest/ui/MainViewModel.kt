package com.composetest.ui

import com.composetest.common.abstracts.BaseViewModel
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.apptheme.GetAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase
) : BaseViewModel<MainUiState>(MainUiState()) {

    init {
        initState()
    }

    private fun initState() {
        asyncFlowTask(
            flowTask = getAppThemeUseCase(),
            onCollect = ::setSystemStyles
        )
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateState {
            it.setAppTheme(appThemeModel)
        }
    }
}