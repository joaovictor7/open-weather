package com.composetest.feature.home.ui.home2

import androidx.activity.compose.BackHandler
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
fun Home2Screen(state: Home2State, onHandleEvent: (Home2Event) -> Unit) {
    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
    ) {
        Button(onClick = { onHandleEvent(Home2Event.ReturnHome) }) {
            Text(text = state.t)
        }
        Text(text = "Home2")
    }
    BackHandler {
        onHandleEvent(Home2Event.ReturnHome)
    }
}

@Preview
@Composable
private fun Preview() {
    ComposeTestTheme {
        Home2Screen(state = Home2State()) { }
    }
}