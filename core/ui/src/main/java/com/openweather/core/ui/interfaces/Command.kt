package com.openweather.core.ui.interfaces

interface Command<Receiver> {
    fun execute(receiver: Receiver)
}