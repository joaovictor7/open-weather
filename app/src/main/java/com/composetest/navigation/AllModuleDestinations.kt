package com.composetest.navigation

import com.composetest.feature.home.destinations.homeModuleDestinations
import com.composetest.feature.login.destinations.loginModuleDestinations
import com.composetest.router.base.ScreenDestination

internal val allModuleDestinations: List<ScreenDestination> = mutableListOf<ScreenDestination>().apply {
    addAll(loginModuleDestinations)
    addAll(homeModuleDestinations)
}

