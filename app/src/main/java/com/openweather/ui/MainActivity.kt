package com.openweather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.designsystem.utils.lifecycleEvent
import com.openweather.feature.login.navigation.loginNavGraph
import com.openweather.feature.home.navigation.homeNavGraph
import com.openweather.core.router.destinations.login.LoginDestination
import com.openweather.core.router.providers.NavHostControllerProvider
import com.openweather.core.ui.interfaces.Command
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navHostControllerProvider: NavHostControllerProvider

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeToEdge()
        setSplashScreen()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LifecycleHandle(viewModel::executeCommand)
            OpenWeatherTheme(
                dynamicColor = uiState.appTheme.dynamicColors,
                theme = uiState.appTheme.theme
            ) {
                Navigation(
                    navHostControllerProvider = navHostControllerProvider,
                    firstScreenDestination = LoginDestination::class
                )
            }

        }
    }

    private fun setEdgeToEdge() = lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle).collect { uiState ->
            enableEdgeToEdge(uiState.statusBarStyle, uiState.navigationBarStyle)
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
        loginNavGraph()
        homeNavGraph()
    }
}

@Composable
private fun LifecycleHandle(onExecuteCommand: (Command<MainCommandReceiver>) -> Unit) {
    val event = lifecycleEvent()
    LaunchedEffect(event) {
        if (event == Lifecycle.Event.ON_RESUME) {
            onExecuteCommand(VerifySession())
        }
    }
}