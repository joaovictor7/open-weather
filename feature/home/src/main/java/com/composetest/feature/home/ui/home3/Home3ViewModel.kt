package com.composetest.feature.home.ui.home3

import com.composetest.common.abstracts.BaseViewModel
import com.composetest.core.router.extensions.getParam
import com.composetest.core.router.destinations.home.Home2Destination
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class Home3ViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<Home3UiState>(Home3UiState()), Home3CommandReceiver {

    init {
        val e = navigationProvider.getParam<Home2Destination>()
        updateState { it.copy(t = e.teste) }
    }

    override fun returnLogin() {
        navigationProvider.navigateToBack(LoginDestination)
        count++
    }

    private companion object {
        var count = 1
    }
}