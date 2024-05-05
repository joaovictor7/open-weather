package com.composetest.router.providers

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import com.composetest.router.domain.enums.Destination
import com.composetest.router.domain.params.base.BaseParam
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NavigationProvider @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    private val savedStateHandle: SavedStateHandle
) {

    private val navController get() = navControllerProvider.navController

    @SuppressWarnings("RestrictedApi")
    fun <Param : BaseParam> navigate(
        param: Param,
        removeCurrentScreen: Boolean = false
    ) = navController?.run {
        findDestination(param.destination.route)?.let { nextNavDestination ->
            navigateToScreenWithParam(
                navDestination = nextNavDestination,
                param = param,
                navOptions = NavOptionsBuilder().apply {
                    if (removeCurrentScreen) removeScreen(currentDestination) else singleLauncher()
                }.build()
            )
        }
    }

    fun navigate(destination: Destination, removeCurrentScreen: Boolean = false) =
        navController?.run {
            navigate(
                route = destination.route,
                navOptions = NavOptionsBuilder().apply {
                    if (removeCurrentScreen) removeScreen(currentDestination) else singleLauncher()
                }.build()
            )
        }

    fun navigateToBack() {
        navController?.popBackStack()
    }

    fun <Param : BaseParam> navigateToBack(param: Param) =
        navController?.previousBackStackEntry?.destination?.let { previousNavDestination ->
            navigateToScreenWithParam(
                navDestination = previousNavDestination,
                param = param,
                navOptions = NavOptionsBuilder().removeScreen(previousNavDestination).build()
            )
        }

    fun <Param : BaseParam> getParam(): Param {
        val currentRoute = navController?.currentDestination?.route.orEmpty()
        return checkNotNull(savedStateHandle.get<Param>(currentRoute))
    }

    private fun <Param> navigateToScreenWithParam(
        navDestination: NavDestination,
        param: Param,
        navOptions: NavOptions? = null
    ) {
        val route = navDestination.route
        if (navDestination.id != 0 && route != null) {
            navController?.navigate(navDestination.id, bundleOf(route to param), navOptions)
        }
    }

    private inner class NavOptionsBuilder {

        private var navOptions = NavOptions.Builder()

        fun singleLauncher() = apply {
            navOptions = navOptions.setLaunchSingleTop(true)
        }

        fun removeScreen(navDestination: NavDestination?) = apply {
            navDestination?.id?.let { destinationId ->
                navOptions = navOptions.setPopUpTo(destinationId, true)
            }
        }

        fun build() = navOptions.build()
    }
}