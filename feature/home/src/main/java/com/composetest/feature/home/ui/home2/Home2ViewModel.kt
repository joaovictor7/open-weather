package com.composetest.feature.home.ui.home2

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.domain.params.home.Home2Param
import com.composetest.router.domain.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2Event, Home2State>(Home2State()) {

    init {
        val e = navigationProvider.getParam<Home2Param>()
        stateValue = stateValue.copy(t = e.teste)
    }

    override fun handleEvent(event: Home2Event) = when (event) {
        is Home2Event.ReturnHome -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack(HomeParam(teste + count))
        count++
    }

    companion object {
        var teste = "teste"
        var count = 1
    }
}