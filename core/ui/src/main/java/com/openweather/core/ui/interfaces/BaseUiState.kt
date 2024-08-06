package com.openweather.core.ui.interfaces

import com.openweather.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam

interface BaseUiState {
    val isLoading: Boolean get() = false
    val errorAlertDialogParam: ErrorAlertDialogParam? get() = null
}