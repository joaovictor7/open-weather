package com.composetest.ui

import com.composetest.common.abstracts.BaseViewModel
import com.composetest.common.di.qualifiers.IoDispatcher
import com.composetest.common.models.AppThemeModel
import com.composetest.core.domain.usecases.apptheme.GetAppThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAppThemeUseCase: GetAppThemeUseCase,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
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
        updateUiState {
            it.setAppTheme(appThemeModel)
        }
    }
}