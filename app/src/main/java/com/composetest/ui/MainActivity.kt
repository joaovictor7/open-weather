package com.composetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.login.navigation.loginNavGraph
import com.composetest.feature.home.navigation.homeNavGraph
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.providers.NavControllerProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navControllerProvider: NavControllerProvider

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setEdgeToEdge()
        setSplashScreen()
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ComposeTestTheme(
                dynamicColor = uiState.appTheme.dynamicColors,
                theme = uiState.appTheme.theme
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Navigation(
                        navControllerProvider = navControllerProvider,
                        firstScreenDestination = LoginDestination::class
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.executeCommand(VerifySession())
    }

    private fun setEdgeToEdge() = lifecycleScope.launch {
        viewModel.uiState.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { uiState ->
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
