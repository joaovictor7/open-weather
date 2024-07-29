package com.composetest.core.router.managers

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavOptions
import com.composetest.common.di.qualifiers.Dispatcher
import com.composetest.common.enums.Dispatchers
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.ResultParam
import com.composetest.core.router.providers.NavHostControllerProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class NavigationManagerImpl @Inject constructor(
    private val navHostControllerProvider: NavHostControllerProvider,
    @Dispatcher(Dispatchers.Main) private val mainDispatcher: CoroutineDispatcher,
    override val savedStateHandle: SavedStateHandle
) : NavigationManager {

    private val navController get() = navHostControllerProvider.navController
    private val navigateBackAvailable
        get() = navController.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

    override fun <Destination : Any> navigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) {
        navController.navigate(
            route = destination,
            navOptions = getNavigateOptions(navigationMode)
        )
    }

    override fun navigateBack() {
        if (navigateBackAvailable) navController.popBackStack()
    }

    override fun <Result : ResultParam> navigateBack(result: Result) {
        if (!navigateBackAvailable) return
        navController.previousBackStackEntry?.savedStateHandle?.set(
            result::class.simpleName.orEmpty(),
            result
        )
        navController.popBackStack()
    }

    override suspend fun <Destination : Any> asyncNavigate(
        destination: Destination,
        navigationMode: NavigationMode?
    ) = withContext(mainDispatcher) {
        navigate(destination, navigationMode)
    }

    override suspend fun asyncNavigateBack() = withContext(mainDispatcher) {
        navigateBack()
    }

    override suspend fun <Result : ResultParam> asyncNavigateBack(result: Result) =
        withContext(mainDispatcher) {
            navigateBack(result)
        }

    private fun getNavigateOptions(mode: NavigationMode?) = NavOptions.Builder().apply {
        val currentDestination = navController.currentDestination
        if (mode != null && currentDestination != null) {
            val destinationId = when (mode) {
                NavigationMode.REMOVE_CURRENT_SCREEN -> currentDestination.id
                NavigationMode.REMOVE_ALL_SCREENS_STACK -> 0
            }
            setPopUpTo(destinationId, true)
        }
        setLaunchSingleTop(true)
    }.build()
}