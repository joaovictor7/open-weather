package com.composetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.router.destinations.Destination
import com.composetest.router.destinations.Destinations
import com.composetest.router.destinations.ScreenDestination
import com.composetest.router.providers.DestinationProvider
import com.composetest.router.providers.NavControllerProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    @Destinations(Destination.LOGIN)
    lateinit var firstDestination: ScreenDestination

    @Inject
    lateinit var navControllerProvider: NavControllerProvider

    @Inject
    lateinit var destinationProvider: DestinationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkMode = isSystemInDarkTheme()
            val viewModel = hiltViewModel<MainViewModel, MainViewModel.Factory> {
                it.create(darkMode)
            }
            val state by viewModel.state.collectAsStateWithLifecycle()
            ComposeTestTheme(
                darkTheme = state.darkTheme,
                dynamicColor = state.dynamicColor
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation(
                        navControllerProvider,
                        firstDestination,
                        destinationProvider.allDestinations
                    )
                }
            }
        }
    }
}

@Composable
private fun Navigation(
    navControllerProvider: NavControllerProvider,
    firstDestination: ScreenDestination,
    allDestinations: List<ScreenDestination>
) {
    val navController = rememberNavController()
    navControllerProvider.setNavController(navController)
    NavHost(navController, firstDestination.route) {
        allDestinations.forEach { destination ->
            composable(route = destination.route) {
                destination.screen.invoke()
            }
        }
    }
}
