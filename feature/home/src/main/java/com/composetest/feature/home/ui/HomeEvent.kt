package com.composetest.feature.home.ui

internal sealed class HomeEvent {
    data object ReturnLogin: HomeEvent()
}