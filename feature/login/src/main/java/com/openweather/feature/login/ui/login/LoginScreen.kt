package com.openweather.feature.login.ui.login

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
import com.openweather.common.extensions.isDarkMode
import com.openweather.core.designsystem.components.alertdialogs.ErrorAlertDialog
import com.openweather.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam
import com.openweather.core.designsystem.components.buttons.Button
import com.openweather.core.designsystem.components.textfields.OutlinedTextField
import com.openweather.core.designsystem.components.textfields.enums.TextFieldIcons
import com.openweather.core.designsystem.components.textfields.params.TextFieldTrailingIconParam
import com.openweather.core.designsystem.compositions.LocalThemeProvider
import com.openweather.core.designsystem.dimensions.spacings
import com.openweather.core.designsystem.extensions.modifiers.verticalTopBackgroundBrush
import com.openweather.core.designsystem.theme.OpenWeatherTheme
import com.openweather.core.ui.interfaces.Command
import com.openweather.core.ui.interfaces.Screen
import com.openweather.feature.login.R

internal object LoginScreen : Screen<LoginUiState, LoginCommandReceiver> {

    @Composable
    override operator fun invoke(
        uiState: LoginUiState,
        onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
    ) {
        if (!uiState.needsLogin) return
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
                    LoginForm(uiState = uiState, onExecuteCommand = onExecuteCommand)
                }
            }
            Text(
                text = uiState.versionName,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = spacings.twelve)
            )
        }
        HandleEffects(onExecuteCommand = onExecuteCommand)
        HandleErrorAlert(errorAlertDialogParam = uiState.errorAlertDialogParam) {
            onExecuteCommand(HandleLoginError)
        }
    }
}

@Composable
private fun ColumnScope.LoginForm(
    uiState: LoginUiState,
    onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit
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
        textValue = uiState.loginFormModel.email,
        placeholderText = stringResource(R.string.feature_login_email_placeholder),
        supportingText = uiState.emailSupportingTextField?.let { stringResource(it) },
        imeAction = ImeAction.Next,
        trailingIconParam = uiState.emailTrailingIconTextField,
        modifier = Modifier.fillMaxWidth(),
        onFocusChanged = {
            if (!it.hasFocus) onExecuteCommand.invoke(CheckShowInvalidEmailMsg)
        }
    ) { email ->
        onExecuteCommand(WriteData(email = email))
    }
    Spacer(Modifier.height(spacings.fourteen))
    OutlinedTextField(
        textValue = uiState.loginFormModel.password,
        labelText = stringResource(R.string.feature_login_password),
        keyboardInput = KeyboardType.Password,
        trailingIconParam = TextFieldTrailingIconParam(
            iconType = TextFieldIcons.SEARCH
        ),
        modifier = Modifier.fillMaxWidth()
    ) { password ->
        onExecuteCommand(WriteData(password = password))
    }
    Spacer(Modifier.height(spacings.eighteen))
    if (uiState.invalidCredentials) {
        Text(
            text = stringResource(R.string.feature_login_invalid_credentials),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(Modifier.height(spacings.ten))
    }
    if (!uiState.isLoading) {
        Button(
            text = stringResource(R.string.feature_login_enter),
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState.enableLoginButton
        ) { onExecuteCommand(Login) }
    } else {
        CircularProgressIndicator(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
private fun HandleEffects(onExecuteCommand: (Command<LoginCommandReceiver>) -> Unit) {
    val currentAppTheme = LocalThemeProvider.current
    LaunchedEffect(Unit) {
        onExecuteCommand(SetCustomTheme(true, currentAppTheme))
    }
    DisposableEffect(Unit) {
        onDispose {
            onExecuteCommand(SetCustomTheme(false, currentAppTheme))
        }
    }
}

@Composable
private fun HandleErrorAlert(
    errorAlertDialogParam: ErrorAlertDialogParam?,
    onDismiss: () -> Unit
) = errorAlertDialogParam?.let {
    ErrorAlertDialog(errorParam = it, onClickDismiss = onDismiss)
}

@Composable
@Preview
private fun Preview() {
    OpenWeatherTheme {
        LoginScreen(
            LoginUiState(
                versionName = "Version",
                invalidCredentials = false,
                isLoading = true
            )
        ) {}
    }
}