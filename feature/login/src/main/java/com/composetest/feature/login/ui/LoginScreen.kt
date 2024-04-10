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
import com.composetest.core.dimensions.spacings
import com.composetest.core.ui.components.buttons.ElevatedButton
import com.composetest.core.ui.components.textfields.OutlinedTextField
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.core.utils.hiltViewModel
import com.composetest.router.providers.NavigationProvider

@Composable
fun LoginScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<LoginViewModel>(navController)
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginContent(state = state, onHandleAction = viewModel::handleAction)
}

@Composable
private fun LoginContent(
    state: LoginState,
    onHandleAction: (LoginAction) -> Unit
) {
    var email: String
    var password: String
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
            ) {
                email = it
            }
            Spacer(modifier = Modifier.height(spacings.fourteen))
            OutlinedTextField(
                labelText = "Senha",
                keyboardInput = KeyboardType.Password,
                modifier = Modifier.fillMaxWidth()
            ) {
                password = it
            }
            Spacer(modifier = Modifier.height(spacings.twentyTwo))
            ElevatedButton(
                text = "Entrar",
                modifier = Modifier.fillMaxWidth()
            ) { onHandleAction.invoke(LoginAction.ClickEnter) }
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
        LoginContent(LoginState()) { }
    }
}