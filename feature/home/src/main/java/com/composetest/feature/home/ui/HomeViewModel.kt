package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.navigation.home.Home2Destination
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<HomeEvent, HomeState>(HomeState()) {

    init {
        val e = navigationProvider.getParam<HomeDestination>()
        stateValue = stateValue.copy(t = e.innerHome.teste)
    }

    override fun handleEvent(event: HomeEvent) = when (event) {
        is HomeEvent.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigate(Home2Destination("tessfdf", "rer"))
    }
}