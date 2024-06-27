package com.composetest.core.ui.interfaces

interface CommandReceiver<Receiver> {
    fun executeCommand(command: Command<Receiver>)
}