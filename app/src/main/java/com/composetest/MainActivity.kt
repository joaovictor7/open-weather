package com.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.feature.login.destinations.LoginDestination
import com.composetest.navigation.allModuleDestinations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme(dynamicColor = BuildConfig.DYNAMIC_COLORS) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
private fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, LoginDestination.route) {
        allModuleDestinations.forEach { screen ->
            composable(route = screen.route) { navBackStackEntry ->
                screen.screen.invoke(navController, navBackStackEntry)
            }
        }
    }
}

@Preview
@Composable
private fun GreetingPreview() {
    ComposeTestTheme {
        Navigation()
    }
}