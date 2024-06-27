package com.composetest.core.ui.interfaces

interface Command<Receiver> {
    fun execute(receiver: Receiver)
}