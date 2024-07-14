package com.composetest.core.designsystem.components.alertdialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam
import com.composetest.core.designsystem.components.buttons.Button

@Composable
fun ErrorAlertDialog(
    errorParam: ErrorAlertDialogParam?,
    onClickDismiss: () -> Unit
) = errorParam?.let {
    AlertDialog(
        onDismissRequest = onClickDismiss,
        icon = { Icon(painter = painterResource(it.iconId), contentDescription = null) },
        iconContentColor = MaterialTheme.colorScheme.error,
        title = { Text(text = stringResource(it.title)) },
        text = { Text(text = stringResource(it.text)) },
        confirmButton = {
            Button(
                text = stringResource(R.string.global_word_close),
                onClick = onClickDismiss
            )
        }
    )
}