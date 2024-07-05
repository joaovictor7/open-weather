package com.composetest.core.router.providers

import android.os.Parcelable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.get
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class NavigationProviderImpl @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    override val savedStateHandle: SavedStateHandle
) : NavigationProvider {

    private val navController get() = navControllerProvider.navController
    private val canNavigateToBack
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    override val currentBackStackEntryFlow get() = navController.currentBackStackEntryFlow

    override fun checkCurrentDestination(destination: Any) =
        navController.graph[destination].id == navController.currentDestination?.id

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

    override fun <Destination : Any> navigateAndClearStack(destination: Destination) {
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
            if (!canNavigateToBack) return
            previousBackStackEntry?.savedStateHandle?.set(
                result::class.simpleName.orEmpty(),
                result
            )
            popBackStack()
        }
    }

    override fun navigateToBack() {
        if (canNavigateToBack) {
            navController.popBackStack()
        }
    }

    override suspend fun <Destination : Any> navigateAsync(
        destination: Destination,
        removeCurrentScreen: Boolean
    ) = withContext(Dispatchers.Main) {
        navigate(destination, removeCurrentScreen)
    }

    override suspend fun <Destination : Any> navigateAndClearStackAsync(
        destination: Destination
    ) = withContext(Dispatchers.Main) {
        navigateAndClearStack(destination)
    }

    override suspend fun <Result : Parcelable> navigateToBackAsync(result: Result) =
        withContext(Dispatchers.Main) {
            navigateToBack(result)
        }

    override suspend fun navigateToBackAsync() = withContext(Dispatchers.Main) {
        navigateToBack()
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