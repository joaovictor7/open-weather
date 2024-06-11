package com.composetest.common.interfaces

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

interface Screen<UiState, CommandReceiver> {

    val uiState: UiState
    val onExecuteCommand: (Command<CommandReceiver>) -> Unit

    @Composable
    @SuppressLint("NotConstructor")
    fun Screen()
}