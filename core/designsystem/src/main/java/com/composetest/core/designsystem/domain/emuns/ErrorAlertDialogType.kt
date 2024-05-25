package com.composetest.core.designsystem.domain.emuns

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.data.domain.throwable.RemoteNetworkThrowable
import com.composetest.core.designsystem.R

enum class ErrorAlertDialogType(
    @DrawableRes val iconId: Int? = null,
    @StringRes val title: Int? = null,
    @StringRes val text: Int? = null
) {
    NONE,
    GENERIC(
        iconId = R.drawable.ic_error_big,
        title = R.string.error_alert_dialog_generic_title,
        text = R.string.error_alert_dialog_generic_text
    ),
    NETWORK(
        iconId = R.drawable.ic_wifi_off,
        title = R.string.error_alert_dialog_network_title,
        text = R.string.error_alert_dialog_network_text
    );

    companion object {
        fun Throwable?.getErrorAlertDialogType() = when (this) {
            null -> NONE
            is RemoteNetworkThrowable -> NETWORK
            else -> GENERIC
        }
    }
}