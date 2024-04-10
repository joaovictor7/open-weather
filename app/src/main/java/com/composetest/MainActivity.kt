package com.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.navigation.allDestinations
import com.composetest.router.base.ScreenDestination
import com.composetest.router.destinations.LoginDestinations
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var loginDestination: LoginDestinations.Login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme(dynamicColor = BuildConfig.DYNAMIC_COLORS) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation(loginDestination)
                }
            }
        }
    }
}

@Composable
private fun Navigation(firstDestination: ScreenDestination) {
    val navController = rememberNavController()
    NavHost(navController, firstDestination.route) {
        allDestinations.forEach { screen ->
            composable(route = screen.route,) {
                screen.screen.invoke(navController)
            }
        }
    }
}

