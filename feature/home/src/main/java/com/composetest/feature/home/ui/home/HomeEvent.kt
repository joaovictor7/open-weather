package com.composetest.feature.home.ui.home

sealed class HomeEvent {
    data object ReturnLogin: HomeEvent()
}