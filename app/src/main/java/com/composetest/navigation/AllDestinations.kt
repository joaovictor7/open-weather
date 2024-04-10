package com.composetest.navigation

import com.composetest.feature.home.destinations.homeDestinations
import com.composetest.feature.login.destinations.loginDestinations
import com.composetest.router.base.ScreenDestination

internal val allDestinations: List<ScreenDestination> = mutableListOf<ScreenDestination>().apply {
    addAll(loginDestinations)
    addAll(homeDestinations)
}