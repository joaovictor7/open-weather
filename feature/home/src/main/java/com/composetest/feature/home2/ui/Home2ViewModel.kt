package com.composetest.feature.home2.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.navigation.HomeDestinations
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2Event, Home2State>(Home2State()) {

    init {
        val e = navigationProvider.getParam<HomeDestinations.Home2>()
        stateValue = stateValue.copy(t = e.teste)
    }

    override fun handleEvent(event: Home2Event) = when (event) {
        is Home2Event.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBackWithParam(HomeDestinations.Home(teste + count))
        count++
    }

    companion object {
        var teste = "teste"
        var count = 1
    }
}