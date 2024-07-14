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
    private val canNavigateBack
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    override val currentBackStackEntryFlow get() = navController.currentBackStackEntryFlow

    override fun currentDestinationCheck(destination: Any) =
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

    override fun <Destination : Any> navigateRemovePrevious(destination: Destination) {
        navController.navigate(
            route = destination,
            navOptions = NavOptionsBuilder().apply {
                removePreviousScreens()
            }.build()
        )
    }

    override fun navigateBack() {
        if (canNavigateBack) navController.popBackStack()
    }

    override fun <Result : Parcelable> navigateBack(result: Result) {
        with(navController) {
            if (!canNavigateBack) return
            previousBackStackEntry?.savedStateHandle?.set(
                result::class.simpleName.orEmpty(),
                result
            )
            popBackStack()
        }
    }

    override suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        removeCurrentScreen: Boolean
    ) = withContext(Dispatchers.Main) {
        navigate(destination, removeCurrentScreen)
    }

    override suspend fun <Destination : Any> asyncNavigateRemovePrevious(
        destination: Destination
    ) = withContext(Dispatchers.Main) {
        navigateRemovePrevious(destination)
    }

    override suspend fun asyncNavigateBack() = withContext(Dispatchers.Main) {
        navigateBack()
    }

    override suspend fun <Result : Parcelable> asyncNavigateBack(result: Result) =
        withContext(Dispatchers.Main) {
            navigateBack(result)
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

        fun removePreviousScreens() = apply {
            navOptions = navOptions.setPopUpTo(0, true)
        }

        fun build() = navOptions.build()
    }
}