package com.openweather.core.router.extensions

import android.os.Parcelable
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.openweather.core.router.managers.NavigationManager
import com.openweather.core.router.providers.NavHostControllerProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import com.openweather.core.router.interfaces.NavType as NavTypes
import kotlin.reflect.KType
import kotlin.reflect.full.companionObjectInstance

inline fun <reified Destination : Any> NavigationManager.getParam(): Destination {
    val navTypes = getNavTypes(Destination::class.companionObjectInstance)
    return savedStateHandle.toRoute<Destination>(navTypes)
}

inline fun <reified Result : Parcelable> NavHostControllerProvider.getResultFlow() =
    navBackStackEntryFlow.transform {
        val result = it.savedStateHandle.get<Result>(Result::class.simpleName.orEmpty())
        if (result != null) emit(result)
    }.flowOn(Dispatchers.Main)

inline fun <reified Destination : Any> NavGraphBuilder.composable(
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    noinline exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    noinline popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    noinline popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    noinline sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    val navTypes = getNavTypes(Destination::class.companionObjectInstance)
    composable<Destination>(
        typeMap = navTypes,
        deepLinks = deepLinks,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        sizeTransform = sizeTransform,
        content = content
    )
}

@PublishedApi
internal fun getNavTypes(companionObject: Any?): Map<KType, NavType<*>> =
    (companionObject as? NavTypes)?.navTypes ?: emptyMap()