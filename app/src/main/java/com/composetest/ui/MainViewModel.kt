package com.composetest.ui

import com.composetest.core.ui.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainAction, MainState>(MainState()) {

    override fun handleAction(action: MainAction) = when (action) {
        else -> Unit
    }
}