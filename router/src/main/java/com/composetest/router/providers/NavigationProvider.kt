package com.composetest.router.providers

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.toRoute
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NavigationProvider @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    @PublishedApi internal val savedStateHandle: SavedStateHandle
) {

    private val navController get() = navControllerProvider.navController

    fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean = false
    ) = navController?.run {
        navigate(
            route = destination,
            navOptions = NavOptionsBuilder().apply {
                if (removeCurrentScreen) removeScreen(currentDestination) else singleLauncher()
            }.build()
        )
    }

    fun <Destination : Any> navigateToBackWithParam(destination: Destination) = navController?.run {
        navigate(
            route = destination,
            navOptions = NavOptionsBuilder().removeScreen(previousBackStackEntry?.destination)
                .build()
        )
    }

    fun navigateToBack() {
        navController?.popBackStack()
    }

    inline fun <reified Destination> getParam(): Destination =
        savedStateHandle.toRoute<Destination>()

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