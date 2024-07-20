package com.composetest.core.router.providers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import androidx.navigation.get
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
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
    private val navigateAvailable
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    override val currentBackStackEntryFlow get() = navController.currentBackStackEntryFlow

    override fun currentDestinationCheck(destination: Any) =
        navController.graph[destination] == navController.currentDestination

    override fun <Destination : Any> navigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) {
        if (!navigateAvailable) return
        navController.navigate(
            route = destination,
            navOptions = getNavOptions(navigationMode)
        )
    }

    override fun navigateBack() {
        if (navigateAvailable) navController.popBackStack()
    }

    override fun <Result : ResultParam> navigateBack(result: Result) {
        if (!navigateAvailable) return
        navController.previousBackStackEntry?.savedStateHandle?.set(
            result::class.simpleName.orEmpty(),
            result
        )
        navController.popBackStack()
    }

    override suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) = withContext(Dispatchers.Main) {
        navigate(destination, navigationMode)
    }

    override suspend fun asyncNavigateBack() = withContext(Dispatchers.Main) {
        navigateBack()
    }

    override suspend fun <Result : ResultParam> asyncNavigateBack(result: Result) =
        withContext(Dispatchers.Main) {
            navigateBack(result)
        }

    private fun getNavOptions(mode: NavigationMode?): NavOptions? {
        val currentDestination = navController.currentDestination
        return if (mode != null && currentDestination != null) {
            NavOptions.Builder().apply {
                val destinationId = when (mode) {
                    NavigationMode.REMOVE_CURRENT_SCREEN -> currentDestination.id
                    NavigationMode.REMOVE_ALL_SCREENS_STACK -> 0
                }
                setPopUpTo(destinationId, true)
            }.build()
        } else null
    }
}