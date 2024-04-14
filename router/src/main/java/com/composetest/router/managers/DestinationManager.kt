package com.composetest.router.managers

import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationManager @Inject constructor(
    @Destinations(Destination.LOGIN) loginDestination: ScreenDestination,
    @Destinations(Destination.HOME) homeDestination: ScreenDestination
) {
    val allDestinations = listOf(
        loginDestination,
        homeDestination
    )
}