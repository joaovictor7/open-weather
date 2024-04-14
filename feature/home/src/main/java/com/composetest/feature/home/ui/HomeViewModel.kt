package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import com.composetest.router.params.home.HomeParam
import com.composetest.router.managers.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Destinations(Destination.HOME) private val homeDestination: ScreenDestination,
    private val navigationManager: NavigationManager,
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    init {
        var e = navigationManager.getParam<HomeParam>(homeDestination)
        var w = e
    }

    override fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationManager.navigateToBack()
    }
}