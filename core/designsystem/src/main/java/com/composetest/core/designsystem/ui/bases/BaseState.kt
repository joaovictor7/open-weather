package com.composetest.core.designsystem.ui.bases

import com.composetest.core.designsystem.domain.emuns.ErrorAlertDialogType

interface BaseState {
    val errorAlertDialogType: ErrorAlertDialogType get() = ErrorAlertDialogType.NONE
}