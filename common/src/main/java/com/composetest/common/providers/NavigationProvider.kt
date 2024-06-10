package com.composetest.common.providers

import androidx.lifecycle.SavedStateHandle

interface NavigationProvider {

    val savedStateHandle: SavedStateHandle

    fun <Destination : Any> navigate(
        destination: Destination,
        removeCurrentScreen: Boolean = false
    )
    fun <Destination : Any> navigateToBack(destination: Destination)
    fun navigateToBack()
}