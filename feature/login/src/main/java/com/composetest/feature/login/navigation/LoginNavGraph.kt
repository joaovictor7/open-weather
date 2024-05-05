package com.composetest.feature.login.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.login.ui.login.LoginScreen
import com.composetest.feature.login.ui.login.LoginViewModel
import com.composetest.router.domain.enums.Destination

fun NavGraphBuilder.loginNavGraph() {
    composable(route = Destination.FEATURE_LOGIN.route) {
        val viewModel = hiltViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(state, viewModel::handleEvent)
    }
}