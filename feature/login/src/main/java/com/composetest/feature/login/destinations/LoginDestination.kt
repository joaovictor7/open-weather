package com.composetest.feature.login.destinations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.composetest.feature.login.ui.LoginScreen
import com.composetest.router.destinations.LoginDestinations

object LoginDestination : LoginDestinations.Login {
    override val route = "login"
    override val screen = @Composable { navController: NavHostController ->
        LoginScreen(navController)
    }
}