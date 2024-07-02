package com.composetest.core.router.providers

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.Flow

interface NavigationProvider {

    val savedStateHandle: SavedStateHandle
    val currentBackStackEntryFlow: Flow<NavBackStackEntry>

    fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean = false
    )

    fun <Destination : Any> navigateAndClearScreenStack(destination: Destination)

    fun <Result : Parcelable> navigateToBack(result: Result)

    fun navigateToBack()
}