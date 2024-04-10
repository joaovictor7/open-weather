package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.destinations.HomeDestinations
import com.composetest.router.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val homeDestinations: HomeDestinations.Home
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    init {
        var e = navigationProvider.getParam<HomeParam>(homeDestinations)
        var w = e
    }

    override fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack()
    }
}