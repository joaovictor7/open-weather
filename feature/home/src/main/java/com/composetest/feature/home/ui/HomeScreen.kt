package com.composetest.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composetest.core.ui.theme.ComposeTestTheme

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
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