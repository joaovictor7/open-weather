package com.composetest.feature.home.ui.home

import com.composetest.common.enums.Theme
import com.composetest.common.interfaces.Command
import com.composetest.common.interfaces.CommandReceiver

internal interface HomeCommandReceiver : CommandReceiver<HomeCommandReceiver> {
    override fun executeCommand(command: Command<HomeCommandReceiver>) {
        command.execute(this)
    }

    fun navigateToHome2()
    fun changeAppTheme(theme: Theme? = null, dynamicColors: Boolean? = null)
}