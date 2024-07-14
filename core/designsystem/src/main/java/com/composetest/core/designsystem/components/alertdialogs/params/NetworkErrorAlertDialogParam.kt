package com.composetest.core.designsystem.components.alertdialogs.params

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.designsystem.R

object NetworkErrorAlertDialogParam : ErrorAlertDialogParam {
    @DrawableRes
    override val iconId: Int = R.drawable.ic_wifi_off
    @StringRes
    override val title: Int = R.string.error_alert_dialog_network_title
    @StringRes
    override val text: Int = R.string.error_alert_dialog_network_text
}