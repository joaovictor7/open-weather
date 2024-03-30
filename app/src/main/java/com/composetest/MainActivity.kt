package com.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.theme.ComposeTestTheme

internal class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                Text(text = "teste", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview
@Composable
fun GreetingPreview() {
    ComposeTestTheme {
        Text(text = "teste", style = MaterialTheme.typography.bodyMedium)
    }
}