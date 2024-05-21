package com.composetest.core.designsystem.components.alerts.params

import androidx.compose.runtime.Composable

internal data class ErrorAlertDialogParam(
    val icon: @Composable () -> Unit,
    val title: @Composable () -> Unit,
    val text: @Composable () -> Unit
)
