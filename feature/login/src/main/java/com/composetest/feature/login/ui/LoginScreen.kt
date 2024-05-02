package com.composetest.feature.login.ui

import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.ui.dimensions.spacings
import com.composetest.core.ui.domain.enums.textfield.TextFieldIcons
import com.composetest.core.ui.components.textfields.params.TextFieldTrailingIconParam
import com.composetest.core.ui.R
import com.composetest.core.ui.components.buttons.Button
import com.composetest.core.ui.components.cards.ElevatedCard
import com.composetest.core.ui.components.textfields.OutlinedTextField
import com.composetest.core.ui.extensions.modifiers.verticalBackgroundBrush
import com.composetest.core.ui.theme.ComposeTestTheme
import com.composetest.core.ui.utils.SetNavigationBarColor

@Composable
fun LoginScreen(
    state: LoginState,
    onHandleEvent: (LoginEvent) -> Unit
) {
//    SetNavigationBarColor()
    Box(
        modifier = Modifier
            .verticalBackgroundBrush()
            .fillMaxSize()
    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(spacings.sixteen)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(spacings.twelve)
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
                        if (!it.hasFocus) onHandleEvent.invoke(LoginEvent.CheckShowInvalidEmailMsg)
                    }
                ) { email ->
                    onHandleEvent.invoke(LoginEvent.WriteData(email = email))
                }
                Spacer(modifier = Modifier.height(spacings.fourteen))
                OutlinedTextField(
                    labelText = stringResource(R.string.feature_login_password),
                    keyboardInput = KeyboardType.Password,
                    trailingIconParam = TextFieldTrailingIconParam(
                        iconType = TextFieldIcons.SEARCH
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) { password ->
                    onHandleEvent.invoke(LoginEvent.WriteData(password = password))
                }
                Spacer(modifier = Modifier.height(spacings.twentyTwo))
                Button(
                    text = stringResource(R.string.feature_login_enter),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = state.enableLoginButton
                ) { onHandleEvent.invoke(LoginEvent.Login) }
            }
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
private fun SetNavigationBarColor() {
    if (!isSystemInDarkTheme()) {
        SetNavigationBarColor(MaterialTheme.colorScheme.primary)
    }
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LoginScreen(LoginState(versionName = "Version")) { }
    }
}