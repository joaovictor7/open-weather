package com.composetest.core.designsystem.components.alertdialogs.extensions

import com.composetest.common.throwables.NetworkThrowable
import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam

val Throwable?.errorAlertDialogParam
    get() = this?.let {
        when (it) {
            is NetworkThrowable -> ErrorAlertDialogParam.networkErrorAlertDialogParam
            else -> ErrorAlertDialogParam.genericErrorAlertDialogParam
        }
    }