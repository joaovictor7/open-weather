package com.composetest.feature.home.ui.home2

import com.composetest.common.abstracts.BaseViewModel
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.navigation.home.Home2Destination
import com.composetest.core.router.navigation.home.HomeDestination
import com.composetest.core.router.navigation.home.navtypes.InnerHome
import com.composetest.common.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class Home2ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home2UiState>(Home2UiState()), Home2CommandReceiver {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        updateState { it.copy(t = e.teste) }
    }

    override fun returnHome() {
        navigationProvider.navigateToBack(
            HomeDestination("teste", InnerHome("rer $count", "343"))
        )
        count++
    }

    private companion object {
        var count = 1
    }
}