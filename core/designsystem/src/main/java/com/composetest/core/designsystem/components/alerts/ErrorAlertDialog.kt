package com.composetest.core.designsystem.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType

@Composable
fun ErrorAlertDialog(
    errorType: ErrorAlertDialogType,
    onClickDismiss: () -> Unit
) = with(errorType) {
    if (this == ErrorAlertDialogType.NONE) return
    AlertDialog(
        onDismissRequest = onClickDismiss,
        icon = errorType.iconId?.let {
            { Icon(painter = painterResource(it), contentDescription = null) }
        },
        iconContentColor = MaterialTheme.colorScheme.error,
        title = title?.let {
            { Text(text = stringResource(it)) }
        },
        text = text?.let {
            { Text(text = stringResource(it)) }
        },
        confirmButton = {
            Button(
                text = stringResource(R.string.error_alert_dialog_button_dismiss),
                onClick = onClickDismiss
            )
        }
    )
}