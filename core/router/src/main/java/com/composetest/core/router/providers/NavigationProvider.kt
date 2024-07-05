package com.composetest.core.router.providers

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.Flow

interface NavigationProvider {

    val savedStateHandle: SavedStateHandle
    val currentBackStackEntryFlow: Flow<NavBackStackEntry>

    fun checkCurrentDestination(destination: Any): Boolean

    fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean = false
    )

    fun <Destination : Any> navigateAndClearStack(destination: Destination)

    fun <Result : Parcelable> navigateToBack(result: Result)

    fun navigateToBack()

    suspend fun <Destination : Any> navigateAsync(
        destination: Destination,
        removeCurrentScreen: Boolean = false
    )

    suspend fun <Destination : Any> navigateAndClearStackAsync(destination: Destination)

    suspend fun <Result : Parcelable> navigateToBackAsync(result: Result)

    suspend fun navigateToBackAsync()
}