package com.composetest.common.interfaces

interface CommandReceiver<Receiver> {
    fun executeCommand(command: Command<Receiver>)
}