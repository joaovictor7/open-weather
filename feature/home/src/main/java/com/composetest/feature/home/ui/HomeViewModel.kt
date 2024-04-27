package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.domain.enums.Destination
import com.composetest.router.domain.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    init {
        var e = navigationProvider.getParam<HomeParam>(Destination.FEATURE_HOME)
        var w = e
    }

    override fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack()
    }
}