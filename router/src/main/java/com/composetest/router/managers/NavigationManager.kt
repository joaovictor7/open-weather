package com.composetest.router.managers

import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.composetest.router.navigation.ScreenDestination
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NavigationManager @Inject constructor(
    navControllerManager: NavControllerManager,
    private val savedStateHandle: SavedStateHandle,
) {

    private val navController: NavHostController? = navControllerManager.navController

    fun <Param> navigateWithArgs(
        destination: ScreenDestination,
        param: Param
    ) {
        navController?.navigateToScreen(destination.route, param)
    }

    fun <Param> navigateToBackWithArgs(param: Param) = navController?.run {
        val previousDestination = previousBackStackEntry?.destination
        navigateToScreen(previousDestination?.route.orEmpty(), param, true)
    }

    fun navigate(destination: ScreenDestination) {
        navController?.navigate(destination.route)
    }

    fun navigateToBack() {
        navController?.popBackStack()
    }

    fun <Param> getParam(destination: ScreenDestination): Param {
        return checkNotNull(savedStateHandle.get<Param>(destination.route))
    }

    @SuppressWarnings("RestrictedApi")
    private fun <Param> NavHostController.navigateToScreen(
        route: String,
        param: Param,
        toBack: Boolean = false
    ) {
        val routeLink = NavDeepLinkRequest.Builder.fromUri(
            NavDestination.createRoute(route).toUri()
        ).build()
        graph.matchDeepLink(routeLink)?.let {
            navigate(
                it.destination.id,
                bundleOf(route to param),
                if (toBack) getNavOptionsForBackNavigation(it.destination.id) else null
            )
        }
    }

    private fun getNavOptionsForBackNavigation(destinationId: Int) = NavOptions.Builder().apply {
        setPopUpTo(destinationId, true)
    }.build()
}