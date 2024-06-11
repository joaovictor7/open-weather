package com.composetest.common.interfaces

interface Command<Receiver> {
    fun execute(receiver: Receiver)
}