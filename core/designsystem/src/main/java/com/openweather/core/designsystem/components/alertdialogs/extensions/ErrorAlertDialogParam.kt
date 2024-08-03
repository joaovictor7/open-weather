package com.openweather.core.designsystem.components.alertdialogs.extensions

import com.openweather.common.throwables.NetworkThrowable
import com.openweather.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam

val Throwable?.errorAlertDialogParam
    get() = this?.let {
        when (it) {
            is NetworkThrowable -> ErrorAlertDialogParam.networkErrorAlertDialogParam
            else -> ErrorAlertDialogParam.genericErrorAlertDialogParam
        }
    }