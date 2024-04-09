package com.composetest.feature.login.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.composetest.core.dimensions.spacings
import com.composetest.core.ui.components.buttons.ElevatedButton
import com.composetest.core.ui.components.textfields.OutlinedTextField
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.core.utils.hiltViewModel

@Composable
fun LoginScreen(
    navHostController: NavHostController
) {
    val viewModel = hiltViewModel<LoginViewModel>(navHostController)
    val state by viewModel.state.collectAsStateWithLifecycle()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(spacings.twelve)
                .align(Alignment.Center)
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacings.fourteen))
            OutlinedTextField(
                labelText = "E-mail",
                placeholderText = "fulano@gmail.com",
                imeAction = ImeAction.Next,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(spacings.fourteen))
            OutlinedTextField(
                labelText = "Senha",
                keyboardInput = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(spacings.twentyTwo))
            ElevatedButton(
                text = "Entrar",
                modifier = Modifier.fillMaxWidth()
            ) { viewModel.handleAction(LoginAction.ClickEnter) }
        }
        Text(
            text = state.versionName,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = spacings.twelve)
        )
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LoginScreen(rememberNavController())
    }
}