package com.composetest.feature.login.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.feature.login.ui.LoginViewModel
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destination

object LoginDestination : ScreenDestination {
    override val destination = Destination.FEATURE_LOGIN
    override val screen = @Composable {
        val viewModel = hiltViewModel<LoginViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        LoginScreen(state, viewModel::handleEvent)
    }
}