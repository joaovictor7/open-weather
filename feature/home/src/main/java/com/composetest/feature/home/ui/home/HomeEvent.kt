package com.composetest.feature.home.ui.home

import com.composetest.core.designsystem.domain.emuns.Theme

sealed interface HomeEvent {
    data object ReturnLogin: HomeEvent
    data class AppThemeHandle(
        val theme: Theme? = null,
        val dynamicColors: Boolean? = null
    ) : HomeEvent
}