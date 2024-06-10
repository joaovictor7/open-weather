package com.composetest.core.designsystem.components.alertdialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.alertdialogs.enums.ErrorAlertDialog
import com.composetest.core.designsystem.components.buttons.Button

@Composable
fun ErrorAlertDialog(
    error: ErrorAlertDialog,
    onClickDismiss: () -> Unit
) = with(error) {
    if (this == ErrorAlertDialog.NONE) return
    AlertDialog(
        onDismissRequest = onClickDismiss,
        icon = error.iconId?.let {
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