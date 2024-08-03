package com.openweather.feature.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.openweather.feature.home.ui.home.HomeScreen
import com.openweather.feature.home.ui.home.HomeViewModel
import com.openweather.core.router.destinations.home.HomeDestination
import com.openweather.core.router.extensions.composable

fun NavGraphBuilder.homeNavGraph() {
    composable<HomeDestination> {
        val viewModel = hiltViewModel<HomeViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        HomeScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}