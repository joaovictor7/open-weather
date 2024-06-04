package com.composetest.feature.home.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.core.domain.models.enums.Theme

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
        Button(onClick = { onHandleEvent(HomeEvent.AppThemeHandle(Theme.AUTO)) }) {
            Text(text = "Auto")
        }
        Button(onClick = { onHandleEvent(HomeEvent.AppThemeHandle(Theme.LIGHT)) }) {
            Text(text = "Light")
        }
        Button(onClick = { onHandleEvent(HomeEvent.AppThemeHandle(Theme.DARK)) }) {
            Text(text = "Dark")
        }
        Button(onClick = { onHandleEvent(HomeEvent.AppThemeHandle(dynamicColors = true)) }) {
            Text(text = "Dynamic on")
        }
        Button(onClick = { onHandleEvent(HomeEvent.AppThemeHandle(dynamicColors = false)) }) {
            Text(text = "Dynamic off")
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