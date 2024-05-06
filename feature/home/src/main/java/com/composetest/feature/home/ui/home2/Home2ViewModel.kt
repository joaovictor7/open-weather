package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.home2.ui.Home2ViewModel
import com.composetest.router.navigation.home.Home2Destination
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.navigation.home.InnerHome
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2Event, Home2State>(Home2State()) {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        stateValue = stateValue.copy(t = e.teste)
    }

    override fun handleEvent(event: Home2Event) = when (event) {
        is Home2Event.ReturnHome -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack(
            HomeDestination(teste + count, InnerHome("rer", "343"))
        )
        count++
    }

    companion object {
        var teste = "teste"
        var count = 1
    }
}