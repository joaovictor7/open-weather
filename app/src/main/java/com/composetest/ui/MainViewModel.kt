package com.composetest.ui

import com.composetest.core.designsystem.ui.bases.BaseViewModel
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.domain.usecases.AppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appThemeUseCase: AppThemeUseCase
) : BaseViewModel<MainAction, MainState>(MainState()) {

    init {
        initState()
    }

    override fun handleEvent(event: MainAction) = when (event) {
        else -> Unit
    }

    private fun initState() {
        asyncFlowTask(
            flowTask = appThemeUseCase.appThemeState,
            onCollect = ::setSystemStyles
        )
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateState {
            it.setAppTheme(appThemeModel)
        }
    }
}