package com.composetest.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.feature.login.ui.LoginViewModel
import com.composetest.router.navigation.LoginDestinations

internal fun NavGraphBuilder.loginNavGraph() {
    composable<LoginDestinations.Login> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(state = state, onHandleEvent = viewModel::handleEvent)
    }
}