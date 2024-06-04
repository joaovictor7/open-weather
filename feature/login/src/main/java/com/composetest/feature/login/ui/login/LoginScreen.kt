package com.composetest.feature.login.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.composetest.core.designsystem.components.alerts.ErrorAlertDialog
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.components.textfields.OutlinedTextField
import com.composetest.core.designsystem.components.textfields.params.TextFieldTrailingIconParam
import com.composetest.core.designsystem.compositions.LocalThemeProvider
import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType
import com.composetest.core.designsystem.dimensions.spacings
import com.composetest.core.designsystem.domain.emuns.TextFieldIcons
import com.composetest.core.designsystem.extensions.isDarkMode
import com.composetest.core.designsystem.extensions.modifiers.verticalTopBackgroundBrush
import com.composetest.core.designsystem.theme.ComposeTestTheme
import com.composetest.feature.login.R

@Composable
fun LoginScreen(
    state: LoginState,
    onHandleEvent: (LoginEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .verticalTopBackgroundBrush(LocalThemeProvider.current.isDarkMode)
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(spacings.eighteen)
        ) {
            Column(modifier = Modifier.padding(spacings.twenty)) {
                LoginForm(state = state, onHandleEvent = onHandleEvent)
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
    HandleEffects(onHandleEvent = onHandleEvent)
    HandleErrorAlert(errorType = state.errorAlertDialogType) {
        onHandleEvent.invoke(LoginEvent.DismissErrorAlertDialog)
    }
}

@Composable
private fun ColumnScope.LoginForm(
    state: LoginState,
    onHandleEvent: (LoginEvent) -> Unit
) {
    Text(
        text = stringResource(R.string.feature_login_login),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.height(spacings.fourteen))
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
    Spacer(Modifier.height(spacings.fourteen))
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
    Spacer(Modifier.height(spacings.eighteen))
    if (state.invalidCredentials) {
        Text(
            text = stringResource(R.string.feature_login_invalid_credentials),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(spacings.ten))
    }
    if (!state.isLoading) {
        Button(
            text = stringResource(R.string.feature_login_enter),
            modifier = Modifier.fillMaxWidth(),
            enabled = state.enableLoginButton
        ) { onHandleEvent.invoke(LoginEvent.Login) }
    } else {
        CircularProgressIndicator(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun HandleEffects(onHandleEvent: (LoginEvent) -> Unit) {
    LaunchedEffect(Unit) {
        onHandleEvent.invoke(LoginEvent.SetCustomTheme(true))
    }
    DisposableEffect(Unit) {
        onDispose {
            onHandleEvent.invoke(LoginEvent.SetCustomTheme(false))
        }
    }
}

@Composable
private fun HandleErrorAlert(errorType: ErrorAlertDialogType, onDismiss: () -> Unit) {
    ErrorAlertDialog(errorType = errorType, onClickDismiss = onDismiss)
}

@Composable
@Preview
private fun Preview() {
    ComposeTestTheme {
        LoginScreen(
            LoginState(
                versionName = "Version",
                invalidCredentials = false,
                isLoading = true
            )
        ) { }
    }
}