package com.composetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.feature.login.navigation.loginNavGraph
import com.composetest.feature.navigation.homeNavGraph
import com.composetest.router.navigation.login.LoginDestination
import com.composetest.router.providers.NavControllerProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navControllerProvider: NavControllerProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            ComposeTestTheme(dynamicColor = state.dynamicColor) {
                Column(
                    modifier = Modifier
                        .safeDrawingPadding()
                        .fillMaxSize()
                ) {
                    Navigation(
                        navControllerProvider = navControllerProvider,
                        firstScreenDestination = LoginDestination::class
                    )
                }
            }
        }
    }
}

@Composable
private fun Navigation(
    navControllerProvider: NavControllerProvider,
    firstScreenDestination: KClass<*>
) {
    val navController = rememberNavController()
    navControllerProvider.setNavController(navController)
    NavHost(
        navController = navController,
        startDestination = firstScreenDestination
    ) {
        loginNavGraph()
        homeNavGraph()
    }
}
