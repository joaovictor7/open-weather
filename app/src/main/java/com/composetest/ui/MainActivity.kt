package com.composetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.composetest.feature.login.navigation.LoginDestination
import com.composetest.router.navigation.ScreenDestination
import com.composetest.router.providers.NavControllerProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navControllerProvider: NavControllerProvider

    @Inject
    lateinit var allScreenDestination: Array<ScreenDestination>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            com.composetest.core.ui.theme.ComposeTestTheme(
                dynamicColor = state.dynamicColor
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation(
                        navControllerProvider = navControllerProvider,
                        firstScreenDestination = LoginDestination,
                        allScreenDestinations = allScreenDestination
                    )
                }
            }
        }
    }
}

@Composable
private fun Navigation(
    navControllerProvider: NavControllerProvider,
    firstScreenDestination: ScreenDestination,
    allScreenDestinations: Array<ScreenDestination>
) {
    val navController = rememberNavController()
    navControllerProvider.setNavController(navController)
    NavHost(navController, firstScreenDestination.destination.route) {
        allScreenDestinations.forEach { screen ->
            composable(
                route = screen.destination.route,
                deepLinks = screen.deepLinks
            ) {
                screen.screen.invoke()
            }
        }
    }
}
