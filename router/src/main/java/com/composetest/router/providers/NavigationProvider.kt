package com.composetest.router.providers

import androidx.core.os.bundleOf
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.composetest.router.domain.enums.Destination
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class NavigationProvider @Inject constructor(
    private val navControllerProvider: NavControllerProvider,
    private val savedStateHandle: SavedStateHandle,
) {

    private val navController get() = navControllerProvider.navController

    fun <Param> navigateWithArgs(destination: Destination, param: Param) {
        navController?.navigateToScreen(destination.route, param)
    }

    fun <Param> navigateToBackWithArgs(param: Param) = navController?.run {
        val previousDestination = previousBackStackEntry?.destination
        navigateToScreen(previousDestination?.route.orEmpty(), param, true)
    }

    fun navigate(destination: Destination) {
        navController?.navigate(destination.route)
    }

    fun navigateToBack() {
        navController?.popBackStack()
    }

    fun <Param> getParam(destination: Destination): Param =
        checkNotNull(savedStateHandle.get<Param>(destination.route))

    @SuppressWarnings("RestrictedApi")
    private fun <Param> NavHostController.navigateToScreen(
        route: String,
        param: Param,
        toBack: Boolean = false
    ) = findDestination(route)?.let { navDestination ->
        navigate(
            navDestination.id,
            bundleOf(route to param),
            if (toBack) getNavOptionsForBackNavigation(navDestination.id) else null
        )
    }

    private fun getNavOptionsForBackNavigation(destinationId: Int) = NavOptions.Builder().apply {
        setPopUpTo(destinationId, true)
    }.build()
}