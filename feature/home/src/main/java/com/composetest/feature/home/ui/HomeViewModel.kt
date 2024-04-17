package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.domain.enums.Destinations
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import com.composetest.router.navigation.qualifiers.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Destination(Destinations.HOME) private val homeDestination: ScreenDestination,
    private val navigationProvider: NavigationProvider,
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    init {
        var e = navigationProvider.getParam<HomeParam>(homeDestination)
        var w = e
    }

    override fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack()
    }
}