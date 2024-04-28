package com.composetest.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.feature.home.ui.HomeViewModel
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.domain.enums.Destination

object HomeDestination: ScreenDestination {
    override val destination = Destination.FEATURE_HOME
    override val screen = @Composable {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(state, viewModel::handleEvent)
    }
}