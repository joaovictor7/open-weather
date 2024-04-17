package com.composetest.router.providers

import com.composetest.router.domain.enums.Destinations
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.navigation.qualifiers.Destination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationProvider @Inject constructor(
    @Destination(Destinations.LOGIN) loginDestination: ScreenDestination,
    @Destination(Destinations.HOME) homeDestination: ScreenDestination
) {
    val allDestinations = listOf(
        loginDestination,
        homeDestination
    )
}