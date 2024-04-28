package com.composetest.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.theme.ComposeTestTheme

@Composable
fun HomeScreen(
    state: HomeState,
    onHandleEvent: (HomeEvent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = { onHandleEvent(HomeEvent.ReturnLogin) }) {
            Text(text = "texto")
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        HomeScreen(state = HomeState()) { }
    }
}