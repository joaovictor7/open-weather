package com.composetest.feature.home.ui.home

import com.composetest.common.enums.Theme
import com.composetest.core.ui.interfaces.Command
import com.composetest.core.ui.interfaces.CommandReceiver

internal interface HomeCommandReceiver :
    CommandReceiver<HomeCommandReceiver> {
    override fun executeCommand(command: Command<HomeCommandReceiver>) {
        command.execute(this)
    }

    fun navigateToHome2()
    fun changeAppTheme(theme: Theme? = null, dynamicColors: Boolean? = null)
}