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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.composetest.core.ui.dimensions.spacings
import com.composetest.core.ui.components.buttons.ElevatedButton
import com.composetest.core.ui.domain.enums.textfield.TextFieldIcons
import com.composetest.core.ui.components.textfields.params.TextFieldTrailingIconParam
import com.composetest.core.ui.R
import com.composetest.core.ui.components.textfields.OutlinedTextField
import com.composetest.core.ui.extensions.modifiers.verticalBackgroundBrush
import com.composetest.core.ui.theme.ComposeTestTheme

@Composable
fun LoginScreen() {
    val viewModel = hiltViewModel<LoginViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoginContent(state = state, onHandleAction = viewModel::handleAction)
}

@Composable
private fun LoginContent(
    state: LoginState,
    onHandleAction: (LoginAction) -> Unit
) {
    var email = String()
    var password = String()
    Box(
        modifier = Modifier
            .verticalBackgroundBrush()
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(spacings.twelve)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.feature_login_login),
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(spacings.fourteen))
            OutlinedTextField(
                labelText = stringResource(R.string.feature_login_email),
                placeholderText = stringResource(R.string.feature_login_email_placeholder),
                supportingText = if (state.invalidEmail)
                    stringResource(R.string.feature_login_invalid_email) else null,
                imeAction = ImeAction.Next,
                trailingIconParam = if (state.invalidEmail)
                    TextFieldTrailingIconParam(iconType = TextFieldIcons.ERROR) else null,
                modifier = Modifier.fillMaxWidth(),
                onFocusChanged = {
                    if (!it.hasFocus) onHandleAction.invoke(LoginAction.CheckEmail)
                }
            ) {
                email = it
                onHandleAction.invoke(LoginAction.WriteData(email, password))
            }
            Spacer(modifier = Modifier.height(spacings.fourteen))
            OutlinedTextField(
                labelText = stringResource(R.string.feature_login_password),
                keyboardInput = KeyboardType.Password,
                trailingIconParam = TextFieldTrailingIconParam(
                    iconType = TextFieldIcons.SEARCH
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                password = it
                onHandleAction.invoke(LoginAction.WriteData(email, password))
            }
            Spacer(modifier = Modifier.height(spacings.twentyTwo))
            ElevatedButton(
                text = stringResource(R.string.feature_login_enter),
                modifier = Modifier.fillMaxWidth(),
                enabled = state.enableLoginButton
            ) { onHandleAction.invoke(LoginAction.Login) }
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