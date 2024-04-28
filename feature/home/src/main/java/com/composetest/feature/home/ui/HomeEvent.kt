package com.composetest.feature.home.ui

sealed class HomeEvent {
    data object ReturnLogin: HomeEvent()
}