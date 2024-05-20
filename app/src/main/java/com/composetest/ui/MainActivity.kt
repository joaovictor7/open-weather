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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.login.navigation.loginNavGraph
import com.composetest.feature.home.navigation.homeNavGraph
import com.composetest.core.router.navigation.login.LoginDestination
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
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            ComposeTestTheme(
                dynamicColor = state.appTheme.dynamicColors,
                darkTheme = state.appTheme.isDarkMode
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

    private fun setEdgeToEdge() = lifecycleScope.launch {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect { state ->
            enableEdgeToEdge(state.statusBarStyle, state.navigationBarStyle)
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
