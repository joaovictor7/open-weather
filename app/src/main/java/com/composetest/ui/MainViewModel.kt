package com.composetest.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.domain.models.AppThemeModel
import com.composetest.core.ui.providers.AppThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appThemeProvider: AppThemeProvider
) : BaseViewModel<MainAction, MainState>(MainState()) {

    init {
        initState()
    }

    override fun handleEvent(event: MainAction) = when (event) {
        else -> Unit
    }

    private fun initState() {
        asyncFlowTask(
            flowTask = appThemeProvider.appThemeState,
            onSuccessTask = ::setSystemStyles
        )
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateState {
            it.initState(
                appThemeModel,
                appThemeModel.systemBarStyles.first,
                appThemeModel.systemBarStyles.second
            )
        }
    }
}