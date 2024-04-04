package com.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.components.textfields.OutlinedTextField
import com.composetest.core.ui.theme.ComposeTestTheme

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Column(modifier = Modifier.fillMaxSize()) {

                }
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ComposeTestTheme {

    }
}