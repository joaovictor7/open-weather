package com.composetest.ui

import androidx.lifecycle.viewModelScope
import com.composetest.core.designsystem.domain.bases.BaseViewModel
import com.composetest.core.designsystem.domain.models.AppThemeModel
import com.composetest.core.designsystem.providers.AppThemeProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            appThemeProvider.appThemeState.collect {
                setSystemStyles(it)
            }
        }
    }

    private fun setSystemStyles(appThemeModel: AppThemeModel) {
        updateState {
            it.initState(appThemeModel)
        }
    }
}