package com.composetest.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.core.utils.hiltViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeViewModel>(navController)
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(state = state, viewModel::handleAction)
}

@Composable
private fun HomeContent(
    state: HomeState,
    onHandleAction: (HomeAction) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { onHandleAction(HomeAction.ReturnLogin) }) {
            Text(text = "texto")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        HomeContent(state = HomeState()) { }
    }
}