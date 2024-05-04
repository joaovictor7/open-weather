package com.composetest.feature.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.core.ui.utils.SetNavigationBarColor

@Composable
internal fun HomeScreen(
    state: HomeState,
    onHandleEvent: (HomeEvent) -> Unit
) {
    SetNavigationBarColor(MaterialTheme.colorScheme.surface)
    Column(modifier = Modifier.fillMaxSize()) {
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