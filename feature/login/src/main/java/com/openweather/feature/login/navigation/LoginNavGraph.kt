package com.openweather.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.openweather.feature.login.ui.login.LoginScreen
import com.openweather.feature.login.ui.login.LoginViewModel
import com.openweather.core.router.destinations.login.LoginDestination
import com.openweather.core.router.extensions.composable

fun NavGraphBuilder.loginNavGraph() {
    composable<LoginDestination> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand)
    }
}