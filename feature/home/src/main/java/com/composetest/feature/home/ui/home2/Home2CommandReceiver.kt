package com.composetest.feature.home.ui.home2

import com.composetest.common.interfaces.Command
import com.composetest.common.interfaces.CommandReceiver

interface Home2CommandReceiver : CommandReceiver<Home2CommandReceiver> {

    override fun executeCommand(command: Command<Home2CommandReceiver>) {
        command.execute(this)
    }

    fun returnHome()
}