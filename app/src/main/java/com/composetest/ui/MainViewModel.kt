package com.composetest.ui

import com.composetest.BuildConfig
import com.composetest.core.ui.bases.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update

@HiltViewModel(assistedFactory = MainViewModel.Factory::class)
class MainViewModel @AssistedInject constructor(
    @Assisted private val darkMode: Boolean
) : BaseViewModel<MainAction, MainState>(MainState()) {

    init {
        init()
    }

    override fun handleAction(action: MainAction) = when (action) {
        else -> Unit
    }

    private fun init() = _state.update {
        it.setTheme(darkMode, BuildConfig.DYNAMIC_COLORS)
    }

    @AssistedFactory
    interface Factory {
        fun create(darkMode: Boolean): MainViewModel
    }

}