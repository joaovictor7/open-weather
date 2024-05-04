package com.composetest.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.login.login.ui.LoginScreen
import com.composetest.feature.login.login.ui.LoginViewModel
import com.composetest.router.navigation.login.LoginDestination

fun NavGraphBuilder.loginNavGraph() {
    composable<LoginDestination> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(state = state, onHandleEvent = viewModel::handleEvent)
    }
}