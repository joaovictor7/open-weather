package com.composetest.feature.home.ui.home

import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.core.ui.providers.AppThemeProvider
import com.composetest.router.navigation.home.Home2Destination
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.providers.NavigationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val navigationProvider: NavigationProvider,
    private val appThemeProvider: AppThemeProvider
) : BaseViewModel<HomeEvent, HomeState>(HomeState()) {

    init {
        val e = navigationProvider.getParam<HomeDestination>(HomeDestination.navTypes)
        updateState { it.copy(t = e.innerHome.teste) }
    }

    override fun handleEvent(event: HomeEvent) = when (event) {
        is HomeEvent.ReturnLogin -> navigateToHome2()
        is HomeEvent.AppThemeHandle -> handleAppTheme(event)
    }

    private fun navigateToHome2() {
        navigationProvider.navigate(Home2Destination("tessfdf", "rer"))
    }

    private fun handleAppTheme(event: HomeEvent.AppThemeHandle) {
        when {
            event.appTheme != null -> {
                appThemeProvider.setAppTheme(event.appTheme)
            }
            event.dynamicColors != null -> {
                appThemeProvider.setDynamicColors(event.dynamicColors)
            }
        }
    }
}