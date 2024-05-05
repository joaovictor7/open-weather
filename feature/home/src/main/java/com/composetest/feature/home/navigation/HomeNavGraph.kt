package com.composetest.feature.home.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.home.ui.home.HomeScreen
import com.composetest.feature.home.ui.home.HomeViewModel
import com.composetest.feature.home.ui.home2.Home2Screen
import com.composetest.feature.home.ui.home2.Home2ViewModel
import com.composetest.router.domain.enums.Destination

fun NavGraphBuilder.homeNavGraph() {
    composable(route = Destination.FEATURE_HOME.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(state, viewModel::handleEvent)
    }
    composable(route = Destination.FEATURE_HOME2.route) {
        val viewModel = hiltViewModel<Home2ViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        Home2Screen(state, viewModel::handleEvent)
    }
}