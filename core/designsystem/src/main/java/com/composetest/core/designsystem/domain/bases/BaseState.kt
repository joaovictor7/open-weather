package com.composetest.core.designsystem.domain.bases

import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType

interface BaseState {
    val alertErrorDialogType: ErrorAlertDialogType get() = ErrorAlertDialogType.NONE
}