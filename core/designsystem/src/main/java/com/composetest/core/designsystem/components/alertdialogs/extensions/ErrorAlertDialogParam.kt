package com.composetest.core.designsystem.components.alertdialogs.extensions

import com.composetest.common.throwables.NetworkThrowable
import com.composetest.core.designsystem.components.alertdialogs.params.GenericErrorAlertDialogParam
import com.composetest.core.designsystem.components.alertdialogs.params.NetworkErrorAlertDialogParam

val Throwable?.errorAlertDialogParam
    get() = this?.let {
        when (it) {
            is NetworkThrowable -> NetworkErrorAlertDialogParam
            else -> GenericErrorAlertDialogParam
        }
    }