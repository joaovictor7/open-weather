package com.composetest.router.providers

import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DestinationProvider @Inject constructor(
    @Destinations(Destination.LOGIN) loginDestination: ScreenDestination,
    @Destinations(Destination.HOME) homeDestination: ScreenDestination
) {
    val allDestinations = listOf(
        loginDestination,
        homeDestination
    )
}