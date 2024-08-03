package com.openweather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.designsystem.utils.lifecycleEvent
import com.openweather.core.router.destinations.home.HomeDestination
import com.openweather.feature.home.navigation.homeNavGraph
import com.openweather.core.router.providers.NavHostControllerProvider
import com.openweather.core.ui.interfaces.Command
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navHostControllerProvider: NavHostControllerProvider

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSplashScreen()
        enableEdgeToEdge()
        setContent {
            LifecycleHandler(viewModel::executeCommand)
            OpenWeatherTheme {
                Navigation(
                    navHostControllerProvider = navHostControllerProvider,
                    firstScreenDestination = HomeDestination::class
                )
            }
        }
    }

    private fun setSplashScreen() {
        installSplashScreen().setKeepOnScreenCondition {
            viewModel.uiState.value.showSplashScreen
        }
    }
}

@Composable
private fun Navigation(
    navHostControllerProvider: NavHostControllerProvider,
    firstScreenDestination: KClass<*>
) {
    val navController = rememberNavController()
    navHostControllerProvider.setNavController(navController)
    NavHost(
        navController = navController,
        startDestination = firstScreenDestination
    ) {
        homeNavGraph()
    }
}

@Composable
private fun LifecycleHandler(onExecuteCommand: (Command<MainCommandReceiver>) -> Unit) {
    val event = lifecycleEvent()
    LaunchedEffect(event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            // TODO: implement re-sync forecast weather
        }
    }
}