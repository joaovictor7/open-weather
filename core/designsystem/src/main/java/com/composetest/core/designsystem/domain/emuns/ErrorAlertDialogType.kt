package com.composetest.core.designsystem.domain.emuns

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.composetest.core.data.domain.exceptions.RemoteNetworkException
import com.composetest.core.designsystem.R
import com.composetest.core.designsystem.components.alerts.params.ErrorAlertDialogParam

enum class ErrorAlertDialogType {
    NONE,
    GENERIC,
    NETWORK;

    internal val properties get() =  when (this) {
        NETWORK -> ErrorAlertDialogParam(
            icon = {
                Icon(painter = painterResource(R.drawable.ic_wifi_off), contentDescription = null)
            },
            title = { Text(stringResource(R.string.error_alert_dialog_network_title)) },
            text = { Text(stringResource(R.string.error_alert_dialog_network_text)) }
        )
        GENERIC -> ErrorAlertDialogParam(
            icon = {
                Icon(painter = painterResource(R.drawable.ic_error_big), contentDescription = null)
            },
            title = { Text(stringResource(R.string.error_alert_dialog_generic_title)) },
            text = { Text(stringResource(R.string.error_alert_dialog_generic_text)) }
        )
        else -> null
    }

    companion object {
        fun Throwable?.getErrorAlertDialogType() = when (this) {
            is RemoteNetworkException -> NETWORK
            null -> NONE
            else -> GENERIC
        }
    }
}