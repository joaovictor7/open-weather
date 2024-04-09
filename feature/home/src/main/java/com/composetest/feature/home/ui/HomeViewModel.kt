package com.composetest.feature.home.ui

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.destinations.HomeDestinations
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val homeDestination: HomeDestinations.Home
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    override fun handleAction(action: HomeAction) {

    }
}