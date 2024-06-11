package com.composetest.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.login.ui.login.LoginScreen
import com.composetest.feature.login.ui.login.LoginViewModel
import com.composetest.core.router.navigation.login.LoginDestination

fun NavGraphBuilder.loginNavGraph() {
    composable<LoginDestination> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginScreen(uiState = uiState, onExecuteCommand = viewModel::executeCommand).Screen()
    }
}