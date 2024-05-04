package com.composetest.feature.navigation

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.composetest.feature.home.ui.HomeScreen
import com.composetest.feature.home.ui.HomeViewModel
import com.composetest.feature.home2.ui.Home2Screen
import com.composetest.feature.home2.ui.Home2ViewModel
import com.composetest.router.navigation.home.Home2Destination
import com.composetest.router.navigation.home.HomeDestination
import com.composetest.router.navigation.home.InnerHome
import com.composetest.router.utils.parcelableNavType
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeNavGraph() {
    composable<HomeDestination>(
        typeMap = mapOf(typeOf<InnerHome>() to parcelableNavType<InnerHome>())
    ) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        HomeScreen(state = state, onHandleEvent = viewModel::handleEvent)
    }
    composable<Home2Destination> {
        val viewModel = hiltViewModel<Home2ViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        Home2Screen(state = state, onHandleEvent = viewModel::handleEvent)
    }
}