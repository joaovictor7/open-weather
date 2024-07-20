package com.composetest.core.designsystem.components.alertdialogs.params

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R

data class ErrorAlertDialogParam(
    @DrawableRes val iconId: Int,
    @StringRes val title: Int,
    @StringRes val text: Int
) {
    companion object {
        val genericErrorAlertDialogParam = ErrorAlertDialogParam(
            iconId = R.drawable.ic_error_big,
            title = R.string.error_alert_dialog_generic_title,
            text = R.string.error_alert_dialog_generic_text
        )
        val networkErrorAlertDialogParam = ErrorAlertDialogParam(
            iconId = R.drawable.ic_wifi_off,
            title = R.string.error_alert_dialog_network_title,
            text = R.string.error_alert_dialog_network_text
        )
    }
}