package com.composetest.feature.home.ui

import androidx.navigation.NavHostController
import com.composetest.core.factories.ViewModelNavigationFactory
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.router.destinations.HomeDestinations
import com.composetest.router.params.home.HomeParam
import com.composetest.router.providers.NavigationProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = HomeViewModel.Factory::class)
class HomeViewModel @AssistedInject constructor(
    @Assisted private val navController: NavHostController,
    private val navigationProvider: NavigationProvider,
    private val homeDestinations: HomeDestinations.Home
) : BaseViewModel<HomeAction, HomeState>(HomeState()) {

    init {
        var e = navigationProvider.getParam<HomeParam>(homeDestinations)
        var w = e
    }

    override fun handleAction(action: HomeAction) = when (action) {
        is HomeAction.ReturnLogin -> navigateToLogin()
    }

    private fun navigateToLogin() {
        navigationProvider.navigateToBack(navController)
    }

    @AssistedFactory
    sealed interface Factory : ViewModelNavigationFactory<HomeViewModel>
}