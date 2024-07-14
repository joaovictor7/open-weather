package com.composetest.core.designsystem.components.alertdialogs.params

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ErrorAlertDialogParam {
    @get:DrawableRes
    val iconId: Int

    @get:StringRes
    val title: Int

    @get:StringRes
    val text: Int
}