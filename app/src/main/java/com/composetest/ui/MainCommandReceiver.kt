package com.composetest.ui

import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface MainCommandReceiver : CommandReceiver<MainCommandReceiver> {

    override fun executeCommand(command: Command<MainCommandReceiver>) {
        command.execute(this)
    }

    fun verifySession()
}