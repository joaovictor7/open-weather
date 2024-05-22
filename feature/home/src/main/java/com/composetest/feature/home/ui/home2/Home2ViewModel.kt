package com.composetest.feature.home.ui.home2

import com.composetest.core.designsystem.domain.bases.BaseViewModel
import com.composetest.core.router.navigation.home.Home2Destination
import com.composetest.core.router.navigation.home.HomeDestination
import com.composetest.core.router.navigation.home.navtypes.InnerHome
import com.composetest.core.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2Event, Home2State>(Home2State()) {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        updateState { it.copy(t = e.teste) }
    }

    override fun handleEvent(event: Home2Event) = when (event) {
        is Home2Event.ReturnHome -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack(
            HomeDestination("teste", InnerHome("rer $count", "343"))
        )
        count++
    }

    companion object {
        var count = 1
    }
}