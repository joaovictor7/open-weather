package com.composetest.feature.home.ui.home

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.domain.params.home.Home2Param
import com.composetest.router.domain.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider
) : BaseViewModel<HomeEvent, HomeState>(HomeState()) {

    init {
        val e = navigationProvider.getParam<HomeParam>()
        stateValue = stateValue.copy(t = e.teste)
    }

    override fun handleEvent(event: HomeEvent) = when (event) {
        is HomeEvent.ReturnLogin -> navigateToHome2()
    }

    private fun navigateToHome2() {
        navigationProvider.navigate(Home2Param("tessfdf"))
    }
}