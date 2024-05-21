package com.composetest.core.designsystem.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.buttons.Button
import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType

@Composable
fun ErrorAlertDialog(
    errorType: ErrorAlertDialogType,
    onClickDismiss: () -> Unit
) {
    if (errorType == ErrorAlertDialogType.NONE) return
    val properties = errorType.properties
    AlertDialog(
        onDismissRequest = onClickDismiss,
        icon = properties?.icon,
        iconContentColor = MaterialTheme.colorScheme.error,
        title = properties?.title,
        text = properties?.text,
        confirmButton = {
            Button(
                text = stringResource(R.string.error_alert_dialog_button_dismiss),
                onClick = onClickDismiss
            )
        }
    )
}