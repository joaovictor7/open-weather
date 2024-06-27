package com.composetest.core.ui.interfaces

import androidx.compose.runtime.Composable

interface Screen<UiState, CommandReceiver> {
    @Composable
    operator fun invoke(
        uiState: UiState,
        onExecuteCommand: (Command<CommandReceiver>) -> Unit
    )
}