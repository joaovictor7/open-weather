package com.composetest.router.providers

import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.composetest.router.base.ScreenDestination

class NavigationProvider(
    private val savedStateHandle: SavedStateHandle
) {

    @SuppressWarnings("RestrictedApi")
    fun <Param> navigateWithArgs(
        navHostController: NavHostController,
        destination: ScreenDestination,
        param: Param
    ) = with(navHostController) {
        val routeLink = NavDeepLinkRequest.Builder.fromUri(
            NavDestination.createRoute(destination.route).toUri()
        ).build()
        graph.matchDeepLink(routeLink)?.let {
            navigate(it.destination.id, bundleOf(destination.route to param))
        } ?: navigate(destination.route)
    }

    fun <Param> getArgs(destination: ScreenDestination): Param {
        return checkNotNull(savedStateHandle.get<Param>(destination.route))
    }

}