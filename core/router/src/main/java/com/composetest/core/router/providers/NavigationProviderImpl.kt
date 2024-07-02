package com.composetest.core.router.providers

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class NavigationProviderImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    override val savedStateHandle: SavedStateHandle
) : NavigationProvider {

    private val navController get() = navControllerProvider.navController

    override val currentBackStackEntryFlow get() = navController.currentBackStackEntryFlow

    override fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean
    ) {
        with(navController) {
            navigate(
                route = destination,
                navOptions = NavOptionsBuilder().apply {
                    if (removeCurrentScreen) popUpScreen(currentDestination) else singleLauncher()
                }.build()
            )
        }
    }

    override fun <Destination : Any> navigateAndClearScreenStack(destination: Destination) {
        with(navController) {
            navigate(
                route = destination,
                navOptions = NavOptionsBuilder().apply {
                    popUpScreen(currentDestination)
                }.build()
            )
        }
    }

    override fun <Result : Parcelable> navigateToBack(result: Result) {
        with(navController) {
            previousBackStackEntry?.savedStateHandle?.set(
                result::class.simpleName.orEmpty(),
                result
            )
            popBackStack()
        }
    }

    override fun navigateToBack() {
        navController.popBackStack()
    }

    private inner class NavOptionsBuilder {

        private var navOptions = NavOptions.Builder()

        fun singleLauncher() = apply {
            navOptions = navOptions.setLaunchSingleTop(true)
        }

        fun popUpScreen(navDestination: NavDestination?) = apply {
            navDestination?.id?.let { destinationId ->
                navOptions = navOptions.setPopUpTo(destinationId, true)
            }
        }

        fun build() = navOptions.build()
    }
}