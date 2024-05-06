package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.theme.ComposeTestTheme

@Composable
fun HomeScreen(state: HomeState, onHandleEvent: (HomeEvent) -> Unit) {
    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
    ) {
        Button(onClick = { onHandleEvent(HomeEvent.ReturnLogin) }) {
            Text(text = state.t)
        }
        Text(text = "Home1")
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        HomeScreen(state = HomeState()) { }
    }
}